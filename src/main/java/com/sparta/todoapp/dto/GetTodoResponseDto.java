package com.sparta.todoapp.dto;

import com.sparta.todoapp.entity.Todo;
import com.sparta.todoapp.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class GetTodoResponseDto {
    private String title;
    private String description;
    private String username;
    private LocalDateTime createdAt;

    public GetTodoResponseDto(Todo todo) {
        this.title = todo.getTitle();
        this.description = todo.getDescription();
        this.username = todo.getUser().getUsername();
        this.createdAt = todo.getCreatedAt();
    }

}
