package com.vochere.chatter.chatter.dto;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String username;
    private String email;
    private String password; // This would be plain-text and should be hashed server-side before saving.
}
