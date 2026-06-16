package br.com.lifeos.gateway.clients;


import br.com.lifeos.gateway.api.dto.UserGatewayDTO;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "user-service") // esta no aplication.properties a definicao dessa chave
@RegisterClientHeaders /*Pegue o cabeçalho Authorization que o React enviou e copie para essa nova requisição HTTP que vai pro microsserviço".*/
public interface UserServiceClient {

    @POST
    @Path("/users")
    Response createUser(UserGatewayDTO userDTO);


}
