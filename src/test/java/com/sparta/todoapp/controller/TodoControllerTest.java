package com.sparta.todoapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.todoapp.config.WebSecurityConfig;
import com.sparta.todoapp.dto.todo.CompleteTodoResponseDto;
import com.sparta.todoapp.dto.todo.GetTodoResponseDto;
import com.sparta.todoapp.dto.todo.TodoRequestDto;
import com.sparta.todoapp.entity.Todo;
import com.sparta.todoapp.entity.User;
import com.sparta.todoapp.repository.TodoRepository;
import com.sparta.todoapp.security.UserDetailsImpl;
import com.sparta.todoapp.service.TodoService;
import java.security.Principal;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@WebMvcTest(
    controllers = {TodoController.class},
    excludeFilters = {
        @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            classes = WebSecurityConfig.class
        )
    }
)
class TodoControllerTest {

    private MockMvc mvc;

    private Principal mockPrincipal;

    @Mock
    TodoRepository todoRepository;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TodoService todoService;


    @BeforeEach
    public void setup() {
        User mockUser = mock(User.class);
        UserDetailsImpl mockUserDetails = mock(UserDetailsImpl.class);
        given(mockUserDetails.getUser()).willReturn(mockUser);

        SecurityContextHolder.getContext()
            .setAuthentication(new UsernamePasswordAuthenticationToken(mockUserDetails, null));

        mvc = webAppContextSetup(context).build();
    }

//    @BeforeEach
//    public void setup() {
//        mvc = MockMvcBuilders.webAppContextSetup(context)
//            .apply(springSecurity(new MockSpringSecurityFilter()))
//            .build();
//    }

//    @BeforeEach
//    public void setup() {
//        mvc = MockMvcBuilders.webAppContextSetup(context)
//            .addFilters(new MockSpringSecurityFilter())
//            .addFilters(new CharacterEncodingFilter("UTF-8", true))
//            .alwaysDo(print())
//            .build();
//    }

//    private void mockUserSetup() {
//        // Mock 테스트 유져 생성
//        String username = "sollertia4351";
//        String password = "robbie1234";
//        String email = "sollertia@sparta.com";
//        User testUser = new User(username, password, email);
//        UserDetailsImpl testUserDetails = new UserDetailsImpl(testUser);
//        mockPrincipal = new UsernamePasswordAuthenticationToken(testUserDetails, "", testUserDetails.getAuthorities());
//    }

    @Test
    @DisplayName("todo 생성 테스트")
    void createTodo() throws Exception {
        mvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new TodoRequestDto("title", "description"))))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @DisplayName("todo 단건 조회 테스트")
    void getTodo() throws Exception {
        Long todoId = 100L;

        mvc.perform(get("/api/todos/{todoId}",todoId))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @DisplayName("todo 전체 조회 테스트")
    void getAllTodos() throws Exception{
        mvc.perform(get("/api/todos"))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @DisplayName("todo 수정 테스트")
    void updateTodo() throws Exception{
        long todoId = 100L;

        TodoRequestDto requestDto = mock(TodoRequestDto.class);

        mvc.perform(put("/api/todos/{todoId}",todoId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @Disabled
    @DisplayName("todo 완료 테스트")
    void completeTodo() throws Exception{
        long todoId = 100L;

        mvc.perform(put("/todos/{todoId}/complete",todoId))
            .andExpect(status().isOk())
            .andDo(print());
    }
}