package com.healthapi.controller;

import com.healthapi.manager.ManagerDto;
import com.healthapi.manager.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("main")
@RequiredArgsConstructor
public class MainController {
    private final ManagerService managerService;

    @GetMapping("/manager")
    public ResponseEntity<ManagerDto> getManager(@Valid ManagerDto managerDto) {
        ManagerDto manager = managerService.findById(managerDto.getId());

        return new ResponseEntity<>(manager, HttpStatus.OK);
    }
}
