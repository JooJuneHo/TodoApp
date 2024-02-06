package com.sparta.todoapp.dto.todo;

import com.sparta.todoapp.entity.Todo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CreateTodoResponseDto {
    private String title;
    private String description;
    private LocalDateTime createdAt;

    public CreateTodoResponseDto(Todo todo) {
        this.title = todo.getTitle();
        this.description = todo.getDescription();
        this.createdAt = todo.getCreatedAt();
    }
}
