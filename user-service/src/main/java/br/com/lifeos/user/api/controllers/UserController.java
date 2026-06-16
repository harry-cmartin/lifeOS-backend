package br.com.lifeos.user.api.controllers;
import br.com.lifeos.shared.security.RoleConstants;

import br.com.lifeos.user.api.dto.UserDTO;
import br.com.lifeos.user.aplication.services.UserService;
import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @Inject
    JsonWebToken jwt;

    @POST// url de cadastro publica
    public Response createUser(@Valid UserDTO userDTO){

        //chama o service para criar o usuario
        UserDTO createdUser = userService.createUser(userDTO);

        return Response.status(Response.Status.CREATED).entity(createdUser).build();


    }

    @GET
    @Authenticated // SEGURANCA : como se fosse um MFA, o quarkus tambem verifica se o usuario esta logado e se tem aas roles necessarias
    @Path("/me")
    public Response getMyPage(){

        String userId = jwt.getSubject();

        if(userId == null){

            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        return Response.ok("O id é " + userId ).build();
    }



}
