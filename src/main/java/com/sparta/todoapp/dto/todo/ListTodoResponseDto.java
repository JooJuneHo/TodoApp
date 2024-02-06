package com.sparta.todoapp.dto.todo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.todoapp.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ListTodoResponseDto {

    @JsonIgnore
    // JsonIgnore 안붙이면?? user -> todo, todo -> user 서로 무한 참조해서 stackoverflow 터짐!!
    private User user;
    private List<GetAllTodoResponseDto> responseDtoList = new ArrayList<>();

    public ListTodoResponseDto(User user) {
        this.user = user;
        this.responseDtoList = user.getTodo().stream().map(GetAllTodoResponseDto::new).toList();
    }
}
