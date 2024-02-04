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

    public LoginResponseDto(String username, String email ,String message) {
        this.username = username;
        this.email = email;
        this.message = message;
    }
}
