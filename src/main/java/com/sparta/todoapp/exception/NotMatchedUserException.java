package com.sparta.todoapp.exception;

public class NotMatchedUserException extends RuntimeException{
    public NotMatchedUserException() {
        super("작성자만 삭제/수정할 수 있습니다.");
    }
}
