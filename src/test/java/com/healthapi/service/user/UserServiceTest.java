package com.healthapi.service.user;

import com.healthapi.domain.user.User;
import com.healthapi.mapper.user.UserMapper;
import com.healthapi.repository.user.UserRepository;
import com.healthapi.response.ApiException;
import com.healthapi.response.ResponseCode;
import com.healthapi.service.dto.user.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Spy
    private UserMapper mapper;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("사용자 목록 조회")
    void findAll() {

    }

    @Test
    @DisplayName("특정 사용자 정보 조회")
    void findById() {
        User user = User.builder()
                .id(0L)
                .name("test")
                .email("test@test.com")
                .delete(false)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(user))
                .thenReturn(Optional.empty())
                .thenThrow(new ApiException(ResponseCode.NOT_FOUND_USER));

        UserDto compareResult = mapper.toDto(user);

        assertEquals(compareResult, userService.findById(0L));       // 검색한 결과와 일치 할 경우
        assertThrows(ApiException.class, () -> userService.findById(0L));     // 조회된 결과가 없을 경우
        assertThrows(ApiException.class, () -> userService.findById(0L));     // 테스트
    }

    @Test
    void create() {
        // given
        UserDto before = UserDto.builder()
                .id(0L)
                .name("test")
                .email("test@test.com")
                .delete(false)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        User after = User.builder()
                .id(0L)
                .name("test")
                .email("test@test.com")
                .delete(false)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.empty())
                .thenThrow(new NullPointerException());
        when(userRepository.save(any()))
                .thenReturn(after)
                .thenThrow(new NullPointerException());

        // when
        UserDto result = userService.create(before);

        // than
        assertEquals(result.getId(), after.getId());
        assertThrows(NullPointerException.class, () -> userService.create(before));
    }

    @Test
    void update() {
        UserDto before = UserDto.builder()
                .id(0L)
                .name("test")
                .email("test@test.com")
                .delete(false)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        User after = User.builder()
                .id(0L)
                .name("test")
                .email("test@test.com")
                .delete(false)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        given(userRepository.findById(anyLong()))
                .willReturn(Optional.empty())
                .willThrow(new NullPointerException());
        given(userRepository.save(any()))
                .willReturn(after)
                .willThrow(new NullPointerException());

        UserDto result = userService.create(before);

        assertEquals(result.getId(), after.getId());
        assertThrows(NullPointerException.class, () -> userService.create(before));
    }

    @Test
    void delete() {
    }
}