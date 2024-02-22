package com.sparta.todoapp.controller;

import com.sparta.todoapp.dto.ResponseDto;
import com.sparta.todoapp.dto.user.LoginRequestDto;
import com.sparta.todoapp.dto.user.SignupRequestDto;
import com.sparta.todoapp.jwt.JwtUtil;
import com.sparta.todoapp.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/user/signup")
    public ResponseEntity<ResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto, BindingResult bindingResult){
        //Valid 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size()>0){
            for (FieldError fieldError : fieldErrors) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            throw new IllegalArgumentException("잘못된 정보입니다.");
        }
        userService.signup(signupRequestDto);

        return ResponseEntity.ok()
                .body(new ResponseDto("회원가입 성공", HttpStatus.OK));
    }

    @PostMapping("/user/login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        try {
            userService.login(loginRequestDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ResponseDto(e.getMessage(),HttpStatus.BAD_REQUEST));
        }
        // Access Token 생성
        String accessToken = jwtUtil.createAccessToken(loginRequestDto.getUsername());
        // cookie에 token 담기
        jwtUtil.addAccessTokenToCookie(accessToken, response);

        // Refresh Token 생성
        String refreshToken = jwtUtil.createRefreshToken(loginRequestDto.getUsername());
        // cookie에 token 담기
        jwtUtil.addRefreshTokenToCookie(refreshToken, response);

        response.setHeader(JwtUtil.ACCESS_TOKEN_HEADER, accessToken);
        response.setHeader(JwtUtil.REFRESH_TOKEN_HEADER, refreshToken);

        return ResponseEntity.ok(new ResponseDto("로그인 성공", HttpStatus.OK));
    }
}
