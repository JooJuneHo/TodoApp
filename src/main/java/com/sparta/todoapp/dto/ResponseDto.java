package com.sparta.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ResponseDto {
    private String msg;
    private HttpStatus statusCode;
}
