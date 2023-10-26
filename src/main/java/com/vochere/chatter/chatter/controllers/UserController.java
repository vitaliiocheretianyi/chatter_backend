package com.vochere.chatter.chatter.controllers;

import org.springframework.web.bind.annotation.*;

import com.vochere.chatter.chatter.dto.RegistrationRequest;
import com.vochere.chatter.chatter.dto.UserDTO;
import com.vochere.chatter.chatter.mapper.UserMapper;
import com.vochere.chatter.chatter.models.User;
import com.vochere.chatter.chatter.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody RegistrationRequest registrationRequest) {
        User user = userMapper.toModel(registrationRequest);
        user.setPassword(registrationRequest.getPassword());

        // System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());

        // String hashedPassword = "/* Hash registrationRequest.getPassword() here */";

        return userMapper.toDTO(userService.saveUser(user));
    }
}
