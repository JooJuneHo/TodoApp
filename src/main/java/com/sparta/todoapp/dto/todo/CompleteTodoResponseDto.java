package com.sparta.todoapp.dto.todo;

import com.sparta.todoapp.entity.Todo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CompleteTodoResponseDto {
    private boolean complete;

    public CompleteTodoResponseDto(Todo todo) {
        this.complete = todo.isComplete();
    }
}
