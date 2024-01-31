package com.sparta.todoapp.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    @Size(min = 4, max = 10)
    @Pattern(regexp = "^[a-z0-9]*$")
    private String username;
    @NotBlank
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String password;
    @Email
    @NotBlank
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}
