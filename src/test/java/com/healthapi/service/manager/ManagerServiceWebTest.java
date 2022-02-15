package com.healthapi.service.manager;

import com.healthapi.config.CustomModelMapper;
import com.healthapi.repository.manager.ManagerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

//@ImportAutoConfiguration(RefreshAutoConfiguration.class)
@WebMvcTest
class ManagerServiceWebTest {
    @InjectMocks
    private ManagerService managerService;

    @Spy
    private CustomModelMapper modelMapper;

    @Mock
    private ManagerRepository managerRepository;

    /**
     * RefreshScope는 전체 컨테이너가 필요
     * SpringBootTest를 사용해야 한다는 뜻인데.. 너무 느림
     * RefreshAutoConfiguration를 사용하면 SpyBean Annotation으로 로딩 가능하지만... 좀..
     */
    @Test
    @DisplayName("RefreshScope 테스트트")
    void findAll() {

    }
}