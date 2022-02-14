package com.healthapi.controller.manager;

import com.healthapi.common.ResponseCode;
import com.healthapi.dto.manager.ManagerDto;
import com.healthapi.service.manager.ManagerService;
import com.healthapi.dto.CommonResult;
import com.healthapi.service.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ManagerController {
    private final ResponseService responseService;
    private final ManagerService managerService;

    @GetMapping("/manager/list")
    public CommonResult<List<ManagerDto>> getManagers() {
        List<ManagerDto> manager = managerService.findAll();

        return responseService.responseResult(ResponseCode.SUCCESS, manager);
    }

    @GetMapping("/manager/{id}")
    public CommonResult<ManagerDto> getManager(@PathVariable("id") long id) {
        return responseService.responseResult(ResponseCode.SUCCESS, managerService.findById(id));
    }

    @PostMapping("/manager")
    public CommonResult<ManagerDto> create(@Valid ManagerDto managerDto) {
        return responseService.responseResult(ResponseCode.SUCCESS, managerService.create(managerDto));
    }

    @PutMapping("/manager")
    public CommonResult<ManagerDto> update(@Valid ManagerDto managerDto) {
        return responseService.responseResult(ResponseCode.SUCCESS, managerService.create(managerDto));
    }

    @DeleteMapping("/manager")
    public CommonResult<ManagerDto> delete(@Valid ManagerDto managerDto) {
        return responseService.responseResult(ResponseCode.SUCCESS, managerService.delete(managerDto));
    }
}
