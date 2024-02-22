package com.sparta.todoapp.exception;

public class NotFoundUserException extends IllegalArgumentException{

    public NotFoundUserException() {
        super("유저가 존재하지 않습니다.");
    }
}
