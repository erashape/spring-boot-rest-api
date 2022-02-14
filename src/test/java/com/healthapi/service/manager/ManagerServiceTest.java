package com.healthapi.service.manager;

import com.healthapi.common.ResponseCode;
import com.healthapi.config.CustomModelMapper;
import com.healthapi.config.advice.exception.CustomException;
import com.healthapi.dto.manager.ManagerDto;
import com.healthapi.entity.manager.ManagerEntity;
import com.healthapi.repository.manager.ManagerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ManagerServiceTest {
    @InjectMocks
    private ManagerService managerService;

    @Spy
    private CustomModelMapper modelMapper;

    @Mock
    private ManagerRepository managerRepository;

    @Test
    @DisplayName("사용자 목록 조회(성공)")
    void findAll() {

    }

    @Test
    @DisplayName("특정 사용자 정보 조회([equals|notEquals|throws])")
    void findById() {
        ManagerEntity managerEntity = ManagerEntity.builder()
                .id(0L)
                .name("test")
                .email("test@test.com")
                .isDelete(false)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        when(managerRepository.findById(anyLong()))
                .thenReturn(Optional.of(managerEntity))
                .thenReturn(Optional.empty())
                .thenThrow(new CustomException(ResponseCode.NOT_FOUND_USER));

        ManagerDto compareResult = modelMapper.strictMapper().map(managerEntity, ManagerDto.class);

        assertEquals(compareResult, managerService.findById(0L));       // 검색한 결과와 일치 할 경우
        assertThrows(CustomException.class, () -> managerService.findById(0L));     // 조회된 결과가 없을 경우
        assertThrows(CustomException.class, () -> managerService.findById(0L));     // 테스트
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}