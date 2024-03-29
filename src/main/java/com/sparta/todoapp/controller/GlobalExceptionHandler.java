package com.sparta.todoapp.controller;

import com.sparta.todoapp.exception.*;
import com.sparta.todoapp.exception.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<ExceptionDto> handleDuplicateUsernameException(DuplicateUsernameException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionDto(e.getMessage(), HttpStatus.CONFLICT));
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ExceptionDto> handleDuplicateEmailException(DuplicateEmailException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionDto(e.getMessage(), HttpStatus.CONFLICT));
    }

    @ExceptionHandler(NotfoundCommentException.class)
    public ResponseEntity<ExceptionDto> handleNotfoundCommentException(NotfoundCommentException e){
        return ResponseEntity.badRequest().body(new ExceptionDto(e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(NotValidationTokenException.class)
    public ResponseEntity<ExceptionDto> handleNotValidationTokenException(NotValidationTokenException e){
        return ResponseEntity.badRequest().body(new ExceptionDto(e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(NotfoundTodoException.class)
    public ResponseEntity<ExceptionDto> handleNotfoundTodoException(NotfoundTodoException e){
        return ResponseEntity.badRequest().body(new ExceptionDto(e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(NotMatchedUserException.class)
    public ResponseEntity<ExceptionDto> handleNotMatchedUserException(NotMatchedUserException e){
        return ResponseEntity.badRequest().body(new ExceptionDto(e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<ExceptionDto> handleNotFoundUserException(NotFoundUserException e){
        return ResponseEntity.badRequest().body(new ExceptionDto(e.getMessage(),
            HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(NotMatchedPasswordException.class)
    public ResponseEntity<ExceptionDto> handleNotMatchedPasswordException(NotMatchedPasswordException e){
        return ResponseEntity.badRequest().body(new ExceptionDto(e.getMessage(),
            HttpStatus.BAD_REQUEST));
    }
}
