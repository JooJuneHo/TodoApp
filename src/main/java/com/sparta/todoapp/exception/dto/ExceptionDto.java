package com.sparta.todoapp.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ExceptionDto {
    private String msg;
    private HttpStatus statusCode;
}
