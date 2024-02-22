package com.sparta.todoapp.exception;

public class NotMatchedPasswordException extends IllegalArgumentException{

    public NotMatchedPasswordException() {
        super("비밀번호가 일치하지 않습니다.");
    }
}
