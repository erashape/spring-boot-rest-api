package com.healthapi.service.user;

import com.healthapi.domain.user.User;
import com.healthapi.mapper.user.UserMapper;
import com.healthapi.repository.user.UserRepository;
import com.healthapi.response.ApiException;
import com.healthapi.response.ResponseCode;
import com.healthapi.service.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);
    private final UserRepository userRepository;

    // 전체 유저를 검색한다.
    public List<UserDto> findAll() {
        log.info("전체 유저를 검색한다.");
        List<User> userList = userRepository.findAll();

        if(userList.isEmpty()) {
            log.info(ResponseCode.NOT_FOUND_USER.getMessage());
            throw new ApiException(ResponseCode.NOT_FOUND_USER);
        }

        return userList.stream().map(UserDto::reverse).toList();

    }

    // 특정 유저를 검색한다.
    public UserDto findById(long id) {
        log.info("특정 유저를 검색한다.");
        Optional<User> manger = userRepository.findById(id);

        if (manger.isEmpty()) {
            log.info(ResponseCode.NOT_FOUND_USER.getMessage());
            throw new ApiException(ResponseCode.NOT_FOUND_USER);
        }

        return UserDto.reverse(manger.get());
    }

    // 유저를 생성한다.
    public UserDto create(UserDto userDto) {
        log.info("유저를 생성한다.");
        boolean isExist = userRepository.existsById(userDto.getId());
        
        // 이미 존재하는 유저
        if(isExist) {
            log.info(ResponseCode.EXIST_USER.getMessage());
            throw new ApiException(ResponseCode.EXIST_USER);
        }

        User user = mapper.toEntity(userDto);

        return mapper.toDto(userRepository.save(user));
    }

    // 특정 유저의 정보를 수정한다.
    public UserDto update(UserDto userDto) {
        log.info("특정 유저의 정보를 수정한다.");
        boolean isExist = userRepository.existsById(userDto.getId());
        
        // 업데이트 할 대상이 없을 경우
        if(!isExist) {
            log.info(ResponseCode.NOT_FOUND_USER.getMessage());
            throw new ApiException(ResponseCode.NOT_FOUND_USER);
        }

        User user = mapper.toEntity(userDto);
        
        return mapper.toDto(userRepository.save(user));
    }

    // 특정 유저의 정보를 삭제한다.
    public UserDto delete(UserDto userDto) {
        log.info("특정 유저의 정보를 삭제한다.");
        Optional<User> user = userRepository.findById(userDto.getId());

        // 삭제 할 대상이 없을 경우
        if (user.isEmpty()) {
            log.info(ResponseCode.NOT_FOUND_USER.getMessage());
            throw new ApiException(ResponseCode.NOT_FOUND_USER);
        }

        userDto.setName("");
        userDto.setDelete(true);

        User result = mapper.toEntity(userDto);

        return mapper.toDto(userRepository.save(result));
    }
}
