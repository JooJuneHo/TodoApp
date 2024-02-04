package com.sparta.todoapp.entity;

import com.sparta.todoapp.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", nullable = false)
    private Todo todo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Comment(CommentRequestDto commentRequestDto, Todo todo, User user) {
        this.user = user;
        this.todo = todo;
        this.title = commentRequestDto.getTitle();
        this.contents = commentRequestDto.getContents();
    }

    public void Update(CommentRequestDto commentRequestDto, User user){
        this.user = user;
        this.title = commentRequestDto.getTitle();
        this.contents = commentRequestDto.getContents();
    }
}
