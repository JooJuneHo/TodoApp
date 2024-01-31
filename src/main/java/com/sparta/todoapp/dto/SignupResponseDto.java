package com.sparta.todoapp.dto;

import com.sparta.todoapp.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupResponseDto {
    private String username;
    private String email;

    public SignupResponseDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
