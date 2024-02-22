package com.sparta.todoapp.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

import com.sparta.todoapp.entity.Todo;
import com.sparta.todoapp.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

@DataJpaTest
public class TodoRepositoryTest {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setup(){
        User user = new User("testuser","12341234","test@naver.com");
        userRepository.save(user);
    }

    @Test
    @DisplayName("생성일시 기준 내림차순 정렬 조회")
    void findAll(){
        // given
        Todo testTodo1 = mock(Todo.class);
        Todo testTodo2 = mock(Todo.class);
        Todo testTodo3 = mock(Todo.class);
        todoRepository.save(testTodo1);
        todoRepository.save(testTodo2);
        todoRepository.save(testTodo3);

        // when
        var resultTodoList = todoRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));

        // then
        assertThat(resultTodoList.get(0)).isEqualTo(testTodo3);
        assertThat(resultTodoList.get(1)).isEqualTo(testTodo2);
        assertThat(resultTodoList.get(2)).isEqualTo(testTodo1);
    }



}
