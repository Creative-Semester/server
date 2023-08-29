package com.sejong.creativesemester.coucil.controller;

import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.coucil.service.CouncilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/council")
public class CouncilController {
    private final CouncilService councilService;

    @GetMapping("/info")
    public SuccessResponse getInfo(Principal principal){
//        log.info("{} : 학생회 정보조회",principal.getName());
        return new SuccessResponse(councilService.findCouncilInfo(principal.getName()));
    }
}
