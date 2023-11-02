package com.sejong.creativesemester.common.controller;

import com.sejong.creativesemester.common.format.success.SuccessResponse;
import jodd.net.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusCheckController {

    @GetMapping("/health-check")
    public SuccessResponse checkHealthStatus(){
        return new SuccessResponse(HttpStatus.ok());
    }
}
