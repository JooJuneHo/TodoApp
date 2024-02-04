package com.sparta.todoapp.service;

import com.sparta.todoapp.dto.SignupRequestDto;
import com.sparta.todoapp.dto.SignupResponseDto;
import com.sparta.todoapp.entity.User;
import com.sparta.todoapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";


    public SignupResponseDto signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        //회원 중복 확인
        Optional<User> checkUserName = userRepository.findByUsername(username);
        if (checkUserName.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        //email 중복확인
        String email = requestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        //사용자 등록
        User user = new User(username, password, email);
        userRepository.save(user);

        return new SignupResponseDto(user);
    }



}
