package com.sparta.todoapp.entityTest;

import static org.junit.jupiter.api.Assertions.*;

import com.sparta.todoapp.dto.comment.CommentRequestDto;
import com.sparta.todoapp.entity.Comment;
import com.sparta.todoapp.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommentTest {

    Comment comment;
    User user;
    CommentRequestDto commentRequestDto;

    @BeforeEach
    void set(){
        comment = new Comment();
        user = new User();
    }

    @Test
    @DisplayName("Comment testComment 테스트")
    void test1(){
        //given
        String title = "제목테스트";
        String contents = "내용테스트";
        commentRequestDto = new CommentRequestDto(title, contents);


        //when
        comment.Update(commentRequestDto, user);

        //then
        assertEquals(title,comment.getTitle());
        assertEquals(contents,comment.getContents());
    }
}
