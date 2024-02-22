package com.sparta.todoapp.serviceTest;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.todoapp.dto.todo.CompleteTodoResponseDto;
import com.sparta.todoapp.dto.todo.CreateTodoResponseDto;
import com.sparta.todoapp.dto.todo.GetTodoResponseDto;
import com.sparta.todoapp.dto.todo.TodoRequestDto;
import com.sparta.todoapp.entity.Todo;
import com.sparta.todoapp.entity.User;
import com.sparta.todoapp.repository.TodoRepository;
import com.sparta.todoapp.repository.UserRepository;
import com.sparta.todoapp.service.TodoService;
import java.util.Optional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    TodoRepository todoRepository;
    @Mock
    UserRepository userRepository;


    @Test
    @DisplayName("createTodo 테스트")
    void test1(){
        //given
        String title = "TestTitle";
        String description = "TestDescription";

        User user = new User();
        TodoRequestDto todoRequestDto = new TodoRequestDto(title,description);


        TodoService todoService = new TodoService(todoRepository, userRepository);

        //when
        CreateTodoResponseDto responseDto = todoService.createTodo(todoRequestDto, user);


        //then
        assertEquals(title, responseDto.getTitle());
        assertEquals(description, responseDto.getDescription());
    }

    @Test
    @DisplayName("getTodo 테스트")
    void test2(){
        //gien
        Long todoId = 100L;

        TodoRequestDto todoRequestDto = new TodoRequestDto("test","test");
        User user = new User();

        Todo todo = new Todo(todoRequestDto, user);

        TodoService todoService = new TodoService(todoRepository, userRepository);


        given(todoRepository.findById(todoId)).willReturn(Optional.of(todo));


        //when
        GetTodoResponseDto getTodoResponseDto = todoService.getTodo(todoId);


        //then
        assertEquals(todo.getTitle(), getTodoResponseDto.getTitle());
        assertEquals(todo.getDescription(), getTodoResponseDto.getDescription());
        assertEquals(todo.getUser().getUsername(), getTodoResponseDto.getUsername());
        assertEquals(todo.getCreatedAt(), getTodoResponseDto.getCreatedAt());
    }

    @Test
    @Disabled
    @DisplayName("getAllTodo 테스트")
    void test3(){

    }

    @Test
    @DisplayName("update 테스트")
    void test4(){
        //given
        Long todoId = 100L;
        Long userId = 100L;

        TodoRequestDto todoRequestDto = new TodoRequestDto("test","test");

        User user = new User();
        user.setId(userId);

        Todo todo = new Todo(todoRequestDto,user);

        TodoService todoService = new TodoService(todoRepository, userRepository);

        given(todoRepository.findById(todoId)).willReturn(Optional.of(todo));

        //when
        GetTodoResponseDto todoResponseDto = todoService.update(todoId,user,todoRequestDto);

        //then
        assertEquals(todoRequestDto.getTitle(),todoResponseDto.getTitle());
        assertEquals(todoRequestDto.getDescription(),todoResponseDto.getDescription());
    }

    @Test
    @DisplayName("complete 테스트")
    void test5(){
        //given
        Long todoId = 100L;
        Long userId = 100L;

        User user = new User();
        user.setId(userId);

        Todo todo = new Todo(new TodoRequestDto("test","test"),user);

        TodoService todoService = new TodoService(todoRepository, userRepository);

        given(todoRepository.findById(todoId)).willReturn(Optional.of(todo));

        //when
        CompleteTodoResponseDto completeTodoResponseDto = todoService.complete(todoId, user);

        //then
        assertEquals(todo.isComplete(),completeTodoResponseDto.isComplete());
    }
}
