package com.vochere.chatter.chatter.service;

import org.springframework.stereotype.Service;

import com.vochere.chatter.chatter.models.ChatRoom;
import com.vochere.chatter.chatter.models.User;
import com.vochere.chatter.chatter.repository.ChatRoomRepository;
import com.vochere.chatter.chatter.repository.MessageRepository;
import com.vochere.chatter.chatter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);

        List<ChatRoom> chatRooms = chatRoomRepository.findAllByParticipantIdsContains(userId);
        for (ChatRoom chatRoom : chatRooms) {
            chatRoom.getParticipantIds().remove(userId);
            chatRoomRepository.save(chatRoom);
        }
    }

    public void changeUserPassword(String userId, String newPassword) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    public void changeUserEmail(String userId, String newEmail) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(newEmail);
        userRepository.save(user);
    }

    public void changeUserUsername(String userId, String newUsername) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(newUsername);
        userRepository.save(user);
    }
}
