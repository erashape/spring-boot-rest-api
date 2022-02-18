package com.healthapi.service.user;

import com.healthapi.config.CustomModelMapper;
import com.healthapi.repository.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

//@ImportAutoConfiguration(RefreshAutoConfiguration.class)
@WebMvcTest
class UserServiceWebTest {
    @InjectMocks
    private UserService userService;

    @Spy
    private CustomModelMapper modelMapper;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("RefreshScope 테스트트")
    void findAll() {

    }
}