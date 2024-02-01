package com.sparta.todoapp.service;

import com.sparta.todoapp.dto.*;
import com.sparta.todoapp.entity.Todo;
import com.sparta.todoapp.entity.User;
import com.sparta.todoapp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public CreateTodoResponseDto createTodo(TodoRequestDto todoRequestDto, User user) {
        Todo todo = todoRepository.save(new Todo(todoRequestDto,user));
        return new CreateTodoResponseDto(todo);
    }

    public GetTodoResponseDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("존재하지않는 일정입니다.")
        );
        return new GetTodoResponseDto(todo);
    }

    public List<GetAllTodoResponseDto> getAllTodos() {
        //작성일 기준 내림차순으로 조회
        List<Todo> todoList = todoRepository.findAll(Sort.by(Sort.Direction.DESC,"createdAt"));
        List<GetAllTodoResponseDto> todoResponseDtoList = new ArrayList<>();

        for (Todo todo : todoList) {
            todoResponseDtoList.add(new GetAllTodoResponseDto(todo));
        }

        return todoResponseDtoList;
    }

    @Transactional
    public GetTodoResponseDto update(Long id, User user, TodoRequestDto todoRequestDto){
        Todo todo = todoRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("존재하지않는 일정입니다.")
        );

        if(!todo.getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("이 일정을 수정할 수 있는 작성자가 아닙니다.");
        }

        todo.Update(todoRequestDto);
        return new GetTodoResponseDto(todo);
    }


    @Transactional
    public CompleteTodoResponseDto complete(Long id, User user) {
        Todo todo = todoRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("존재하지않는 일정입니다.")
        );

        if(!todo.getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("이 일정을 완료할 수 있는 작성자가 아닙니다.");
        }

        todo.Complete();

        return new CompleteTodoResponseDto(todo);
    }
}
