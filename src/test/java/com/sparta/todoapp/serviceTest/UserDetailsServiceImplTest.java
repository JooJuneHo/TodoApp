package com.sparta.todoapp.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import com.sparta.todoapp.entity.User;
import com.sparta.todoapp.repository.UserRepository;
import com.sparta.todoapp.security.UserDetailsImpl;
import com.sparta.todoapp.security.UserDetailsServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("UserdetailService 테스트")
    void loadUserByUsername() {
        //given
        String username = "username";

        User user = new User(username,"12341234","email@naver.com");

        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl(userRepository);

        given(userRepository.findByUsername(username)).willReturn(Optional.of(user));

        //when
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);


        //then
        assertEquals(username, userDetails.getUsername());
    }
}