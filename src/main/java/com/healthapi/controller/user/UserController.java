package com.healthapi.controller.user;

import com.healthapi.response.ResponseCode;
import com.healthapi.service.dto.user.UserDto;
import com.healthapi.service.user.UserService;
import com.healthapi.response.ApiResponse;
import com.healthapi.service.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final ResponseService responseService;
    private final UserService userService;

    @GetMapping("/user/list")
    public ApiResponse<List<UserDto>> findUsers() {
        List<UserDto> user = userService.findAll();

        return responseService.responseResult(ResponseCode.SUCCESS, user);
    }

    @GetMapping("/user/{id}")
    public ApiResponse<UserDto> findUser(@PathVariable("id") long id) {
        return responseService.responseResult(ResponseCode.SUCCESS, userService.findById(id));
    }

    @PostMapping("/user")
    public ApiResponse<UserDto> create(@Valid UserDto userDto) {
        return responseService.responseResult(ResponseCode.SUCCESS, userService.create(userDto));
    }

    @PutMapping("/user")
    public ApiResponse<UserDto> update(@Valid UserDto userDto) {
        return responseService.responseResult(ResponseCode.SUCCESS, userService.create(userDto));
    }

    @DeleteMapping("/user")
    public ApiResponse<UserDto> delete(@Valid UserDto userDto) {
        return responseService.responseResult(ResponseCode.SUCCESS, userService.delete(userDto));
    }
}
