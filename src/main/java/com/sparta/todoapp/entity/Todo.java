package com.sparta.todoapp.entity;

import com.sparta.todoapp.dto.CompleteTodoResponseDto;
import com.sparta.todoapp.dto.TodoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "todos")
public class Todo extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private boolean complete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Todo(TodoRequestDto todoRequestDto, User user) {
        this.title = todoRequestDto.getTitle();
        this.description = todoRequestDto.getDescription();
        this.user = user;
    }

    public void Update(TodoRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
    }

    public void Complete(){
        this.complete = true;
    }
}
