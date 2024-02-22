package com.sparta.todoapp.controller;

import com.sparta.todoapp.dto.ResponseDto;
import com.sparta.todoapp.dto.comment.CommentRequestDto;
import com.sparta.todoapp.dto.comment.CommentResponseDto;
import com.sparta.todoapp.security.UserDetailsImpl;
import com.sparta.todoapp.service.CommentService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/todos/{todoId}/comment")
    public CommentResponseDto createComment(
            @PathVariable Long todoId,
            @RequestBody CommentRequestDto commentRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(todoId, commentRequestDto, userDetails.getUser());
    }

    @PutMapping("/todos/{todoId}/comment/{commentId}")
    public CommentResponseDto updateComment(
            @PathVariable Long todoId,
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto commentRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(todoId, commentId, commentRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/todos/{todoId}/comment/{commentId}")
    public ResponseEntity<ResponseDto> deleteComment(
            @PathVariable Long todoId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.deleteComment(todoId, commentId, userDetails.getUser());
    }
}
