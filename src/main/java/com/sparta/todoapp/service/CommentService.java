package com.sparta.todoapp.service;

import com.sparta.todoapp.dto.CommentRequestDto;
import com.sparta.todoapp.dto.CommentResponseDto;
import com.sparta.todoapp.entity.Comment;
import com.sparta.todoapp.entity.Todo;
import com.sparta.todoapp.entity.User;
import com.sparta.todoapp.repository.CommentRepository;
import com.sparta.todoapp.repository.TodoRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;

    public CommentResponseDto createComment(Long todoId, CommentRequestDto commentRequestDto, User user) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() ->
                new IllegalArgumentException("댓글을 작성할 게시글이 없습니다.")
        );

        Comment comment = new Comment(commentRequestDto, todo, user);

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("올바르지 않은 작성자입니다.");
        }

        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long todoId, Long commentId, CommentRequestDto commentRequestDto, User user) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() ->
                new IllegalArgumentException("댓글을 작성할 게시글이 없습니다.")
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 댓글입니다.")
        );

        if(!todo.getId().equals(comment.getTodo().getId())){
            throw new IllegalArgumentException("다른 작성글입니다.");
        }

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("올바르지 않은 작성자입니다.");
        }

        comment.Update(commentRequestDto, user);

        return new CommentResponseDto(comment);
    }


    @Transactional
    public String deleteComment(Long todoId, Long commentId, User user, HttpServletResponse response) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() ->
                new IllegalArgumentException("댓글을 작성할 게시글이 없습니다.")
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 댓글입니다.")
        );

        if(!todo.getId().equals(comment.getTodo().getId())){
            throw new IllegalArgumentException("다른 작성글입니다.");
        }

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("올바르지 않은 작성자입니다.");
        }

        commentRepository.deleteById(commentId);

        return "댓글 삭제 성공, 상태 코드 : " + response.getStatus();
    }
}
