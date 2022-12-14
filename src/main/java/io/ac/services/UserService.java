package io.ac.services;

import io.ac.UserRepository;
import io.ac.dtos.LoginRequestDTO;
import io.ac.entities.UserEntity;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserService {
    private Logger LOGGER = Logger.getLogger(UserService.class);

    @Inject
    UserRepository repository;

    public UserEntity findByUsernameAndPassword(LoginRequestDTO loginRequestDTO) {
        LOGGER.info("Find user with username="+loginRequestDTO.getUsername());
        return repository.findByUsernameAndPassword(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
    }

}
