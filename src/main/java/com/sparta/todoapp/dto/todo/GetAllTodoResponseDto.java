package com.sparta.todoapp.dto.todo;

import com.sparta.todoapp.entity.Todo;
import com.sparta.todoapp.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class GetAllTodoResponseDto {
    private String title;
    private String username;
    private boolean complete;
    private LocalDateTime createdAt;


    public GetAllTodoResponseDto(Todo todo) {
        this.title = todo.getTitle();
        this.username = todo.getUser().getUsername();
        this.complete = todo.isComplete();
        this.createdAt = todo.getCreatedAt();
    }

}
