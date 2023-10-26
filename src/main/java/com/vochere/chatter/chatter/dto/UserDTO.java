package com.vochere.chatter.chatter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String username;
    private String email;
    // You might not want to expose the password, even if it's hashed, in the DTO.
    // Future fields like profilePicture, status, etc can be added here as well.
}
