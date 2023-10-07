package com.sejong.creativesemester.promise.controller;

import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.promise.service.PromiseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/promise")
@RestController
public class PromiseController {
    private final PromiseService promiseService;
    @ApiOperation(value = "학생회 공약 이행 체크api")
    @PostMapping("/{promiseId}")
    public SuccessResponse implementPromise(@ApiIgnore Authentication authentication
            , @Parameter(name = "체크할 공약id") @PathVariable Long promiseId){
        promiseService.implementPromise(authentication,promiseId);
        return SuccessResponse.ok("공약 이행을 축하합니다");
    }
}
