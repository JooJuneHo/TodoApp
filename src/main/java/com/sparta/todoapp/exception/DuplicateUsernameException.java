package com.sparta.todoapp.exception;

public class DuplicateUsernameException extends RuntimeException{

    public DuplicateUsernameException(){
        super("중복된 username 입니다.");
    }
}
