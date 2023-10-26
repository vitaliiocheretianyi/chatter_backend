package com.vochere.chatter.chatter.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String username;
    private String email; // Optional, based on your requirements
    private String password; // Store a hashed password, not plain text!
    // add other fields like profilePicture, status, etc in the future
}
