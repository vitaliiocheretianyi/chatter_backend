package com.vochere.chatter.chatter.service;

import org.springframework.stereotype.Service;
import com.vochere.chatter.chatter.models.User;
import com.vochere.chatter.chatter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Additional service methods as needed
}
