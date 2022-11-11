package io.ac.services;

import io.ac.UserRepository;
import io.ac.dtos.LoginRequestDTO;
import io.ac.entities.UserEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserService {

    @Inject
    UserRepository repository;

    public UserEntity findByUsernameAndPassword(LoginRequestDTO loginRequestDTO) {
        return repository.findByUsernameAndPassword(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
    }

}
