package com.sparta.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginResponseDto {
    private String username;
    private String email;

    private String message;
    private String statusCode;

    public LoginResponseDto(String username, String email ,String message, String statusCode) {
        this.username = username;
        this.email = email;
        this.message = message;
        this.statusCode = statusCode;
    }
}
