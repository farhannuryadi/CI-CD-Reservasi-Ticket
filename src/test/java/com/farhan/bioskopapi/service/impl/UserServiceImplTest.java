package com.farhan.bioskopapi.service.impl;

import com.farhan.bioskopapi.entity.UserEntity;
import com.farhan.bioskopapi.repository.UserRepository;
import com.farhan.bioskopapi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);

    UserEntity user1;
    UserEntity user2;
    UserEntity user3;

    @BeforeEach
    void setUp(){
        user1 = new UserEntity("farhanryd", "Farhan Nuryadi", "Depok", "farhan6@gmail.com", "123456");
        user2 = new UserEntity("tirtakrmh", "Tirta Karimah", "Jakarta Timur", "tirtakrmh@gmail.com", "123456");
        user3 = new UserEntity("hann", "Hann Joo", "Tokyo", "hann@gmail.com", "123456");
        Mockito.when(userRepository.save(user1)).thenReturn(user1);
        Mockito.when(userRepository.findByUsername("farhanryd")).thenReturn(Optional.of(user1));
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user1, user2));
    }

    @Test
    @DisplayName("test create user berhasil")
    void saveUserEntity() {
        assertEquals(user3.getUsername(), userService.save(user3).getUsername());
    }

    @Test
    @DisplayName("test mencari user dengan username")
    void findByUsername() {
        assertNotNull(userService.findOne("farhanryd"));
    }

    @Test
    @DisplayName("test dapatkan semua data user")
    void findAllUserEntity() {
        assertNotNull(userService.findAll());
    }
}