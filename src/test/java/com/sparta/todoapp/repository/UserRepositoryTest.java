package com.sparta.todoapp.repository;

import com.sparta.todoapp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void test1(){
        User user = new User();
        user.setUsername("test");
        user.setPassword("12341234");
        user.setEmail("test@naver.com");

        User save = userRepository.save(user);

        System.out.println("save.getId() = " + save.getId());
    }
}