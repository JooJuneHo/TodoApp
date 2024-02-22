package com.sparta.todoapp.service;

import com.sparta.todoapp.dto.todo.*;
import com.sparta.todoapp.entity.Todo;
import com.sparta.todoapp.entity.User;
import com.sparta.todoapp.exception.NotMatchedUserException;
import com.sparta.todoapp.exception.NotfoundTodoException;
import com.sparta.todoapp.repository.TodoRepository;
import com.sparta.todoapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public CreateTodoResponseDto createTodo(TodoRequestDto todoRequestDto, User user) {
        Todo todo = new Todo(todoRequestDto,user);
        todoRepository.save(todo);
        return new CreateTodoResponseDto(todo);
    }

    public GetTodoResponseDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                NotfoundTodoException::new
        );
        return new GetTodoResponseDto(todo);
    }

    public List<ListTodoResponseDto> getAllTodos() {
        //todo 작성일 기준 내림차순으로 조회

        List<User> userList = userRepository.findAll();
        List<ListTodoResponseDto> listTodoResponseDto = new ArrayList<>();

        for(User user : userList){
            listTodoResponseDto.add(new ListTodoResponseDto(user));
        }

         return listTodoResponseDto;
    }


    @Transactional
    public GetTodoResponseDto update(Long id, User user, TodoRequestDto todoRequestDto){
        Todo todo = todoRepository.findById(id).orElseThrow(
                NotfoundTodoException::new
        );

        if(!todo.getUser().getId().equals(user.getId())){
            throw new NotMatchedUserException();
        }

        todo.Update(todoRequestDto);
        return new GetTodoResponseDto(todo);
    }


    @Transactional
    public CompleteTodoResponseDto complete(Long id, User user) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                NotfoundTodoException::new
        );

        if(!todo.getUser().getId().equals(user.getId())){
            throw new NotMatchedUserException();
        }

        todo.Complete();

        return new CompleteTodoResponseDto(todo);
    }
}


