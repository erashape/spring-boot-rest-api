package com.healthapi.manager;

import com.healthapi.advice.exception.CustomException;
import com.healthapi.common.ResponseCode;
import com.healthapi.config.CustomModelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagerService {
    private final CustomModelMapper modelMapper;
    private final ManagerRepository managerRepository;

    // 전체 유저를 검색한다.
    public List<ManagerDto> findAll() {
        List<ManagerEntity> managerList = managerRepository.findAll();

        if(managerList.isEmpty()) {
            throw new CustomException(ResponseCode.NOT_FOUND_USER.getCode(), "등록된 유저가 없음");
        }

        return managerList.stream().map(manager -> modelMapper.strictMapper().map(manager, ManagerDto.class)).toList();

    }

    // 특정 유저를 검색한다.
    public ManagerDto findById(long id) {
        Optional<ManagerEntity> mangerEntity = managerRepository.findById(id);

        if (mangerEntity.isEmpty()) {
            throw new CustomException(ResponseCode.NOT_FOUND_USER.getCode());
        }

        return modelMapper.strictMapper().map(mangerEntity.get(), ManagerDto.class);
    }

    // 특정 유저를 생성한다.
    public ManagerDto create(ManagerDto managerDto) {
        Optional<ManagerEntity> mangerEntity = managerRepository.findById(managerDto.getId());
        
        // 이미 존재하는 유저
        if(mangerEntity.isEmpty()) {
            throw new CustomException(ResponseCode.EXIST_USER.getCode());
        }

        ManagerEntity managerEntity = modelMapper.strictMapper().map(managerDto, ManagerEntity.class);
        managerEntity = managerRepository.save(managerEntity);

        return modelMapper.strictMapper().map(managerEntity, ManagerDto.class);
    }

    // 특정 유저의 정보를 수정한다.
    public ManagerDto update(ManagerDto managerDto) {
        Optional<ManagerEntity> managerEntity = managerRepository.findById(managerDto.getId());
        
        // 업데이트 할 대상이 없을 경우
        if(managerEntity.isEmpty()) {
            throw new CustomException(ResponseCode.NOT_FOUND_USER.getCode());
        }
        
        return modelMapper.strictMapper().map(managerRepository.save(managerEntity.get()), ManagerDto.class);
    }

    // 특정 유저의 정보를 삭제한다.
    public ManagerDto delete(ManagerDto managerDto) {
        Optional<ManagerEntity> mangerEntity = managerRepository.findById(managerDto.getId());

        // 삭제 할 대상이 없을 경우
        if (mangerEntity.isEmpty()) {
            throw new CustomException(ResponseCode.NOT_FOUND_USER.getCode());
        }

        managerDto.setName("");
        managerDto.setDelete(true);

        ManagerEntity result = modelMapper.strictMapper().map(managerDto, ManagerEntity.class);
        result = managerRepository.save(result);

        return modelMapper.strictMapper().map(result, ManagerDto.class);
    }
}
