package com.health.controller.user;

import com.health.response.ApiResponse;
import com.health.service.dto.user.UserDto;
import com.health.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user/list")
    public ApiResponse<List<UserDto>> findAll() {
        List<UserDto> user = userService.findAll();

        return ApiResponse.result(user);
    }

    @GetMapping("/user/{id}")
    public ApiResponse<UserDto> findById(@PathVariable("id") long id) {
        return ApiResponse.result(userService.findById(id));
    }

    @PostMapping("/user")
    public ApiResponse<UserDto> create(@Valid UserDto userDto) {
        return ApiResponse.result(userService.create(userDto));
    }

    @PutMapping("/user")
    public ApiResponse<UserDto> update(@Valid UserDto userDto) {
        return ApiResponse.result(userService.create(userDto));
    }

    @DeleteMapping("/user")
    public ApiResponse<UserDto> delete(@Valid UserDto userDto) {
        return ApiResponse.result(userService.delete(userDto));
    }
}
