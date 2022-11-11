package io.ac.services;

import io.ac.dtos.LoginRequestDTO;
import io.ac.entities.UserEntity;

import javax.inject.Singleton;

@Singleton
public class UserService {

    public UserEntity findByUsernameAndPassword(LoginRequestDTO loginRequestDTO) {
        return UserEntity.findByUsernameAndPassword(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
    }

}
