//package com.sparta.todoapp.security;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sparta.todoapp.dto.user.LoginRequestDto;
//import com.sparta.todoapp.jwt.JwtUtil;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.io.IOException;
//
//@Slf4j(topic = "로그인 및 JWT 생성")
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    private final JwtUtil jwtUtil;
//    private final JwtTokenError jwtTokenError;
//
//
//    public JwtAuthenticationFilter(JwtUtil jwtUtil, JwtTokenError jwtTokenError) {
//        this.jwtUtil = jwtUtil;
//        this.jwtTokenError = jwtTokenError;
//        setFilterProcessesUrl("/api/user/login");
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        log.info("로그인 시도");
//        try {
//            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
//
//            return getAuthenticationManager().authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            requestDto.getUsername(),
//                            requestDto.getPassword(),
//                            null
//                    )
//            );
//        } catch (IOException e) {
//            log.error(e.getMessage());
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
//        log.info("로그인 성공");
//        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
//
//        //Access Token 생성
//        String token = jwtUtil.createAccessToken(username);
//        jwtUtil.addAccessTokenToCookie(token, response);
//
//        //Refresh Token 생성
//        String refreshToken = jwtUtil.createRefreshToken(username);
//        jwtUtil.addRefreshTokenToCookie(refreshToken, response);
//
////        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
////
////        response.setCharacterEncoding("UTF-8");
////        response.getWriter().write("로그인 성공, 상태코드 : " + response.getStatus());
//        String message = "로그인 성공";
//        jwtTokenError.messageToClient(response, 200, message, "success");
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
//        log.info("로그인 실패");
//
////        response.setStatus(400);
////        response.setCharacterEncoding("UTF-8");
////        response.getWriter().write("로그인 실패, 상태코드 : " + response.getStatus());
//        String message = "회원을 찾을 수 없습니다.";
//        jwtTokenError.messageToClient(response, 400, message, "error");
//    }
//
//}