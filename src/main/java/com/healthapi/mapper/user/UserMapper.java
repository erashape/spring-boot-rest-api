package com.healthapi.mapper.user;

import com.healthapi.domain.user.User;
import com.healthapi.mapper.GenericMapper;
import com.healthapi.service.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<UserDto, User> {
}
