package com.health.mapper.user;

import com.health.domain.user.User;
import com.health.mapper.GenericMapper;
import com.health.service.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<UserDto, User> {
}
