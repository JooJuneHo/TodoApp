package com.sparta.todoapp.controller;

import com.sparta.todoapp.dto.CommentRequestDto;
import com.sparta.todoapp.dto.CommentResponseDto;
import com.sparta.todoapp.security.UserDetailsImpl;
import com.sparta.todoapp.service.CommentService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
    public String deleteComment(
            @PathVariable Long todoId,
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            HttpServletResponse response){
        return commentService.deleteComment(todoId, commentId, userDetails.getUser(), response);
    }
}
