package com.sejong.creativesemester.promise.controller;

import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.common.meta.DistributeLock;
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
    private static final String PROMISE_KEY = "PROMISE_";
    private final PromiseService promiseService;

    @ApiOperation(value = "학생회 공약 이행 체크api")
    @DistributeLock(identifier = PROMISE_KEY, key = "#promiseId")
    @PostMapping("/{promiseId}")
    public SuccessResponse implementPromise(@ApiIgnore Authentication authentication
            , @Parameter(name = "체크할 공약id") @PathVariable Long promiseId) {
        boolean isImpl = promiseService.implementPromise(authentication, promiseId);
        if (isImpl) {
            return SuccessResponse.ok("공약을 이행했습니다.");
        }
        return SuccessResponse.ok("공약이행을 취소하였습니다.");
    }
}
