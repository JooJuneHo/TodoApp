package com.sparta.todoapp.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import com.sparta.todoapp.dto.ResponseDto;
import com.sparta.todoapp.dto.comment.CommentRequestDto;
import com.sparta.todoapp.dto.comment.CommentResponseDto;
import com.sparta.todoapp.dto.todo.TodoRequestDto;
import com.sparta.todoapp.entity.Comment;
import com.sparta.todoapp.entity.Todo;
import com.sparta.todoapp.entity.User;
import com.sparta.todoapp.repository.CommentRepository;
import com.sparta.todoapp.repository.TodoRepository;
import com.sparta.todoapp.service.CommentService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    TodoRepository todoRepository;
    @Mock
    CommentRepository commentRepository;

    @Test
    @DisplayName("createComment 테스트")
    void test1(){
        Long todoId = 100L;
        Long userId = 100L;

        CommentRequestDto commentRequestDto = new CommentRequestDto("test","test");

        User user = new User();
        user.setId(userId);

        Todo todo = new Todo(new TodoRequestDto("title","description"),user);

        given(todoRepository.findById(todoId)).willReturn(Optional.of(todo));


        CommentService commentservice = new CommentService(todoRepository,commentRepository);

        CommentResponseDto commentResponseDto = commentservice.createComment(todoId,commentRequestDto,user);

        assertEquals(commentRequestDto.getTitle(), commentResponseDto.getTitle());
        assertEquals(commentRequestDto.getContents(), commentResponseDto.getContents());
    }


    @Test
    @DisplayName("updateComment 테스트")
    void test2(){
        Long todoId = 100L;
        Long userId = 100L;
        Long commentId = 100L;

        CommentRequestDto commentRequestDto = new CommentRequestDto("test update", "test update");

        User user = new User();
        user.setId(userId);

        Todo todo = new Todo(new TodoRequestDto("title", "title"),user);
        todo.setId(todoId);

        Comment comment = new Comment(commentRequestDto, todo, user);

        given(todoRepository.findById(todoId)).willReturn(Optional.of(todo));
        given(commentRepository.findById(commentId)).willReturn(Optional.of(comment));

        CommentService commentService = new CommentService(todoRepository, commentRepository);

        CommentResponseDto commentResponseDto = commentService.updateComment(todoId, commentId, commentRequestDto, user);

        assertEquals(comment.getTitle(), commentResponseDto.getTitle());
        assertEquals(comment.getContents(), commentResponseDto.getContents());
    }

    @Test
    @DisplayName("deleteComment 테스트")
    void test3(){
        Long todoId = 100L;
        Long userId = 100L;
        Long commentId = 100L;

        User user = new User();
        user.setId(userId);

        Todo todo = new Todo();
        todo.setId(todoId);

        CommentRequestDto commentRequestDto = new CommentRequestDto("test update", "test update");

        Comment comment = new Comment(commentRequestDto, todo, user);

        given(todoRepository.findById(todoId)).willReturn(Optional.of(todo));
        given(commentRepository.findById(commentId)).willReturn(Optional.of(comment));

        CommentService commentService = new CommentService(todoRepository, commentRepository);

        ResponseEntity<ResponseDto> response = commentService.deleteComment(todoId, commentId, user);

        List<Comment> commentList = commentRepository.findAll();

        assertThat(commentList).isEmpty();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("댓글 삭제 성공, 상태 코드 : "+HttpStatus.OK, response.getBody().getMsg()+response.getBody().getStatusCode());
    }
}
