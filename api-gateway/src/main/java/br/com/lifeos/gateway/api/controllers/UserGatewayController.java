
package br.com.lifeos.gateway.api.controllers;

import br.com.lifeos.gateway.api.dto.UserGatewayDTO;
import br.com.lifeos.gateway.clients.UserServiceClient;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.Collections;
import java.util.UUID;

@Path("/api/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserGatewayController {

    // Controller de cadastro de usuario para o front

    @RestClient
    UserServiceClient userServiceClient;

    @Inject
    Keycloak keycloakAdmin; // O Quarkus vai usar o Producer para injetar isso

    @POST
    @Path("/register") // O front vai chamar POST /api/user/register
    public Response submitUserCreation(UserGatewayDTO dto){

        // 1. Configura a senha do usuário
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(dto.getSenha());
        credential.setTemporary(false);

        // 2. Prepara o objeto para o Keycloak
        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(dto.getEmail()); // Login será o email
        kcUser.setEmail(dto.getEmail());
        kcUser.setFirstName(dto.getName());
        kcUser.setCredentials(Collections.singletonList(credential));
        kcUser.setEnabled(true);

        // 3. Cria no Keycloak (Realm "life-os" baseado na sua prop)
        Response kcResponse = keycloakAdmin.realm("life-os").users().create(kcUser);

        if (kcResponse.getStatus() != 201) {
            return Response.status(kcResponse.getStatus())
                           .entity("Erro ao criar usuário no provedor de identidade")
                           .build();
        }

        // 4. Pega o UUID que o Keycloak gerou
        String keycloakId = CreatedResponseUtil.getCreatedId(kcResponse);

        // 5. Atribui o ID ao DTO antes de mandar pro Microsserviço
        dto.setId(UUID.fromString(keycloakId));

        // 6. Envia para o user-service salvar no PostgreSQL
        Response msResponse = userServiceClient.createUser(dto);

        if (msResponse.getStatus() != (Response.Status.CREATED.getStatusCode())) {

            keycloakAdmin.realm("life-os").users().delete(keycloakId);

            return Response.status(Response.Status.CONFLICT).entity("Erro ao criar usuario no banco de dados").build();
        }

        return msResponse;
    }
}
