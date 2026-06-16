package br.com.lifeos.user.aplication.services;

import br.com.lifeos.user.api.dto.UserDTO;
import br.com.lifeos.user.aplication.mappers.UserMapper;
import br.com.lifeos.user.domain.entities.User;
import br.com.lifeos.user.domain.repository.UserReposity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

// Serviço de manipulacao de usuarios (CRUD), tem relacao com o controller
@ApplicationScoped
public class UserService {

    @Inject
    UserReposity userReposity;

    @Inject
    UserMapper userMapper;

    @Transactional
    public UserDTO createUser(UserDTO userDTO){

        if (userDTO == null){
            System.out.println("UserDTO is null");
        }
        User user = userMapper.toEntity(userDTO);

        if(user != null){
            userReposity.persist(user);
        }

        return userMapper.toDto(user);

    }



}
