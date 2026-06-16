package br.com.lifeos.user.aplication.mappers;

import br.com.lifeos.user.api.dto.UserDTO;
import br.com.lifeos.user.domain.entities.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.Null;

@ApplicationScoped
// Classe de mapeamento de entidades para DTOs
public class UserMapper {

    public User toEntity(UserDTO userDTO) {


        try {

            User user = new User();

            user.setId(userDTO.getId());
            user.setNome(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setTelefone(userDTO.getTelefone());
            user.setCpf(userDTO.getCpf());
            user.setTemaEscuro(userDTO.isTemaEscuro());
            user.setUploadedAt(userDTO.getUpdatedAt());

            return user;

        }catch (Exception e) {

            return null;
        }




    }

    public UserDTO toDto(User user){

        try {
            UserDTO userDTO = new UserDTO();

            userDTO.setId(user.getId());
            userDTO.setName(user.getNome());
            userDTO.setEmail(user.getEmail());
            userDTO.setTelefone(user.getTelefone());
            userDTO.setCpf(user.getCpf());
            userDTO.setTemaEscuro(user.isTemaEscuro());
            userDTO.setUpdatedAt(user.getUploadedAt());

            return userDTO;


        }catch (Exception e){

            return null;
        }



    }

}
