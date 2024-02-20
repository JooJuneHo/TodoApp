package com.sparta.todoapp.EntityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sparta.todoapp.dto.todo.TodoRequestDto;
import com.sparta.todoapp.entity.Todo;
import com.sparta.todoapp.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TodoTest {


    Todo todo;
    User user;
    TodoRequestDto todoRequestDto;

    @BeforeEach
    void setup(){
        todo = new Todo();
        user = new User();
    }

    @Test
    @DisplayName("Todo Update 테스트")
    void test1(){
        //given
        String title = "제목test";
        String description = "내용test";
        todoRequestDto = new TodoRequestDto(title, description);

        //when
        todo.Update(title, description);

        //then
        assertEquals(title,todo.getTitle());
        assertEquals(description,todo.getDescription());

    }

    @Test
    @DisplayName("Todo complete 테스트")
    void test2(){
        //given


        //when
        todo.Complete();

        //then
        assertTrue(todo.isComplete());
    }

}
