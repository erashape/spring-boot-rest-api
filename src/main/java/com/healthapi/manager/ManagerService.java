package com.healthapi.manager;

import com.healthapi.advice.exception.CustomException;
import com.healthapi.common.ResponseCode;
import com.healthapi.config.CustomModelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagerService {
    private final CustomModelMapper modelMapper;
    private final ManagerRepository managerRepository;

    public ManagerDto findById(long id) {
        Optional<ManagerEntity> mangerEntity = managerRepository.findById(id);

        if (mangerEntity.isEmpty()) {
            throw new CustomException(ResponseCode.NOT_FOUND_USER);
        }

        return modelMapper.strictMapper().map(mangerEntity.get(), ManagerDto.class);
    }
}
