package com.sparta.todoapp.dto.comment;

import com.sparta.todoapp.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private String title;
    private String contents;

    public CommentResponseDto(Comment comment) {
        this.title = comment.getTitle();
        this.contents = comment.getContents();
    }
}
