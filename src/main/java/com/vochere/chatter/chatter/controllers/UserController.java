package com.vochere.chatter.chatter.controllers;

import org.springframework.web.bind.annotation.*;
import com.vochere.chatter.chatter.dto.AuthenticationResponse;
import com.vochere.chatter.chatter.dto.LoginRequest;
import com.vochere.chatter.chatter.dto.RegistrationRequest;
import com.vochere.chatter.chatter.dto.UserDTO;
import com.vochere.chatter.chatter.mapper.UserMapper;
import com.vochere.chatter.chatter.middleware.JwtUtil;
import com.vochere.chatter.chatter.models.User;
import com.vochere.chatter.chatter.service.UserService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    @Lazy
    private UserService userService;

    @Autowired
    @Lazy
    private UserMapper userMapper;

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Autowired
    @Lazy
    private JwtUtil jwtUtil;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody RegistrationRequest registrationRequest) {
        User user = userMapper.toModel(registrationRequest);
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        System.out.println(user.getUsername());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());

        return userMapper.toDTO(userService.saveUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        /*
         * try {
         * System.out.println(loginRequest.getUsername());
         * System.out.println(loginRequest.getPassword());
         * authenticationManager.authenticate(
         * new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
         * loginRequest.getPassword()));
         * } catch (Exception e) {
         * e.printStackTrace();
         * System.out.println("Incorrect username or password");
         * return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
         * body("Incorrect username or password");
         * }
         * 
         * System.out.println("Passed all checks, generating token");
         * final UserDetails userDetails =
         * userService.loadUserByUsername(loginRequest.getUsername());
         * System.out.println("User: " + userDetails.getUsername());
         * final String jwt = jwtUtil.generateToken(userDetails.getUsername());
         * System.out.println("JWT: " + jwt);
         * 
         * return ResponseEntity.ok(new AuthenticationResponse(jwt));
         */
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword()));
            // If authentication was successful, return 200 OK
            return ResponseEntity.ok("Login successful");
        } catch (Exception e) {
            // If authentication failed, return 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Incorrect username or password");
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<?> changePassword(@PathVariable String userId, @RequestBody Map<String, String> body) {
        String newPassword = body.get("newPassword");
        userService.changeUserPassword(userId, passwordEncoder.encode(newPassword));
        return ResponseEntity.ok("Password changed successfully");
    }

    @PutMapping("/{userId}/email")
    public ResponseEntity<?> changeEmail(@PathVariable String userId, @RequestBody Map<String, String> body) {
        String newEmail = body.get("newEmail");
        userService.changeUserEmail(userId, newEmail);
        return ResponseEntity.ok("Email changed successfully");
    }

    @PutMapping("/{userId}/username")
    public ResponseEntity<?> changeUsername(@PathVariable String userId, @RequestBody Map<String, String> body) {
        String newUsername = body.get("newUsername");
        userService.changeUserUsername(userId, newUsername);
        return ResponseEntity.ok("Username changed successfully");
    }
}
