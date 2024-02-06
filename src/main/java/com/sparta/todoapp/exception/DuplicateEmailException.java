package com.sparta.todoapp.exception;

public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException(){
        super("중복된 Email 입니다.");
    }

}
