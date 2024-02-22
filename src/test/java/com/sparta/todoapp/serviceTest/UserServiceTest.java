package com.sparta.todoapp.serviceTest;

import static org.junit.jupiter.api.Assertions.*;

import com.sparta.todoapp.dto.user.SignupRequestDto;
import com.sparta.todoapp.dto.user.SignupResponseDto;
import com.sparta.todoapp.repository.UserRepository;
import com.sparta.todoapp.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;


    @Test
    @DisplayName("회원가입 테스트")
    void test1(){
        //given
        String username = "TestUser";
        String password = "jo12341234";
        String email = "TestUser@naver.com";

        SignupRequestDto signupRequestDto = new SignupRequestDto(username,password,email);

        UserService userService = new UserService(userRepository, passwordEncoder);

        //when
        SignupResponseDto signupResponseDto = userService.signup(signupRequestDto);

        //then
        assertEquals(username, signupResponseDto.getUsername());
        assertEquals(email, signupResponseDto.getEmail());
    }

}
