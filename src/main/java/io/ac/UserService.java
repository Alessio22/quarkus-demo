package io.ac;

import javax.inject.Singleton;

@Singleton
public class UserService {

    public UserEntity findByUsernameAndPassword(LoginRequestDTO loginRequestDTO) {
        return UserEntity.findByUsernameAndPassword(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
    }

}
