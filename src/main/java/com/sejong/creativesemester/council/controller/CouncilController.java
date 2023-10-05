package com.sejong.creativesemester.council.controller;

import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.council.service.CouncilService;
import com.sejong.creativesemester.council.service.res.CouncilInfoResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/council")
public class CouncilController {
    private final CouncilService councilService;

    @ApiOperation(value = "학생회의 정보를 조회해주는 api")
    @GetMapping("/info")
    public SuccessResponse<CouncilInfoResponse> getInfo(@ApiIgnore Principal principal){
//        log.info("{} : 학생회 정보조회",principal.getName());
        return new SuccessResponse(councilService.findCouncilInfo(principal.getName()));
    }
}