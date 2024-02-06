package com.sparta.todoapp.service;

import com.sparta.todoapp.dto.comment.CommentRequestDto;
import com.sparta.todoapp.dto.comment.CommentResponseDto;
import com.sparta.todoapp.entity.Comment;
import com.sparta.todoapp.entity.Todo;
import com.sparta.todoapp.entity.User;
import com.sparta.todoapp.exception.NotMatchedUserException;
import com.sparta.todoapp.exception.NotfoundCommentException;
import com.sparta.todoapp.exception.NotfoundTodoException;
import com.sparta.todoapp.repository.CommentRepository;
import com.sparta.todoapp.repository.TodoRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;



    public CommentResponseDto createComment(Long todoId, CommentRequestDto commentRequestDto, User user) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                NotfoundTodoException::new
        );

        Comment comment = new Comment(commentRequestDto, todo, user);

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new NotMatchedUserException();
        }

        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long todoId, Long commentId, CommentRequestDto commentRequestDto, User user) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                NotfoundTodoException::new
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                NotfoundCommentException::new
        );

        if(!todo.getId().equals(comment.getTodo().getId())){
            throw new NotMatchedUserException();
        }

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new NotMatchedUserException();
        }

        comment.Update(commentRequestDto, user);

        return new CommentResponseDto(comment);
    }


    //Todo : ResponseEntity => return type 으로 찾아보기
    public ResponseEntity<String> deleteComment(Long todoId, Long commentId, User user) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                NotfoundTodoException::new
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                NotfoundCommentException::new
        );

        if(!todo.getId().equals(comment.getTodo().getId())){
            throw new NotMatchedUserException();
        }

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new NotMatchedUserException();
        }

        commentRepository.deleteById(commentId);

        return ResponseEntity.ok()
                .body("댓글 삭제 성공, 상태 코드 : " + HttpStatus.OK);
    }


}
