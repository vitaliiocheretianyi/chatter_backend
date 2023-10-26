package com.vochere.chatter.chatter.mapper;

import org.springframework.stereotype.Component;

import com.vochere.chatter.chatter.dto.RegistrationRequest;
import com.vochere.chatter.chatter.dto.UserDTO;
import com.vochere.chatter.chatter.models.User;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    public User toModel(RegistrationRequest registrationRequest) {
        return User.builder()
                .username(registrationRequest.getUsername())
                .email(registrationRequest.getEmail())
                .build();
    }
}
