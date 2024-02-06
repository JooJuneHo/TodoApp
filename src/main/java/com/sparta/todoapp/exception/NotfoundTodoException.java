package com.sparta.todoapp.exception;

public class NotfoundTodoException extends IllegalArgumentException {
    public NotfoundTodoException() {
        super("존재하지 않는 일정입니다.");
    }
}
