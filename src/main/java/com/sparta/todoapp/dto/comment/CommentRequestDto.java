package com.sparta.todoapp.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private String title;
    private String contents;
}
