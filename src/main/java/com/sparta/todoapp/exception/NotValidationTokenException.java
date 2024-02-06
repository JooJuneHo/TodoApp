package com.sparta.todoapp.exception;

public class NotValidationTokenException extends RuntimeException {
    public NotValidationTokenException() {
        super("토큰이 유효하지 않습니다.");
    }
}
