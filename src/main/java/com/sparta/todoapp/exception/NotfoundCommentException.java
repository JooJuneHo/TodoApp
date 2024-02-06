package com.sparta.todoapp.exception;

public class NotfoundCommentException extends IllegalArgumentException{
    
    public NotfoundCommentException() {
        super("존재하지 않는 댓글입니다.");
    }
}
