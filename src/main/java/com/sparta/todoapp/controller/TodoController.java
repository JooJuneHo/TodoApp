package com.sparta.todoapp.controller;

import com.sparta.todoapp.dto.*;
import com.sparta.todoapp.security.UserDetailsImpl;
import com.sparta.todoapp.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.UserDatabase;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TodoController {

    private final TodoService todoservice;

    @PostMapping("/todos")
    public CreateTodoResponseDto createTodo(@RequestBody TodoRequestDto todoRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return todoservice.createTodo(todoRequestDto, userDetails.getUser());
    }

    @GetMapping("/todos/{id}")
    public GetTodoResponseDto getTodo(@PathVariable Long id) {
        return todoservice.getTodo(id);
    }

    @GetMapping("/todos")
    public List<GetAllTodoResponseDto> getAllTodos() {
        return todoservice.getAllTodos();
    }

    @PutMapping("/todos/{id}")
    public GetTodoResponseDto updateTodo(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody TodoRequestDto todoRequestDto) {
        return todoservice.update(id, userDetails.getUser(),todoRequestDto);
    }

    @PutMapping("/todos/complete/{id}")
    public CompleteTodoResponseDto completeTodo(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return todoservice.complete(id, userDetails.getUser());
    }
}
