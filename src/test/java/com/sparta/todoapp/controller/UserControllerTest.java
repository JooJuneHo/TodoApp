package com.sparta.todoapp.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.todoapp.config.WebSecurityConfig;
import com.sparta.todoapp.dto.user.LoginRequestDto;
import com.sparta.todoapp.dto.user.LoginResponseDto;
import com.sparta.todoapp.dto.user.SignupRequestDto;
import com.sparta.todoapp.jwt.JwtUtil;
import com.sparta.todoapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(
    controllers = {UserController.class},
    excludeFilters = {
        @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            classes = WebSecurityConfig.class
        )
    }
)
class UserControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    UserService userService;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
            .apply(springSecurity(new MockSpringSecurityFilter()))
            .build();
    }

    @Test
    @DisplayName("로그인 Page")
    @WithMockUser()
    void test1() throws Exception {
        //given
        String username = "test";
        String password = "12341234";
        String email = "test@naver.com";

        LoginRequestDto requestDto = new LoginRequestDto(username, password);
        LoginResponseDto responseDto = new LoginResponseDto(username, email, "로그인 성공");

        // when - then
        mvc.perform(post("/api/user/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new LoginRequestDto(username, password))))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @DisplayName("회원 가입 요청 처리")
    void test2() throws Exception {
        // given
        String username = "test123";
        String password = "12341234";
        String email = "test@naver.com";

        SignupRequestDto signupRequestDto = new SignupRequestDto(username, password,email);


        // when - then
        mvc.perform(post("/api/user/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signupRequestDto)))
            .andExpect(status().isOk())
            .andDo(print());
    }
}

