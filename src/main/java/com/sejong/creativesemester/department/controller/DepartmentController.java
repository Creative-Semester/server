package com.sejong.creativesemester.department.controller;

import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/department")
@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/promises/percentage")
    public SuccessResponse getPromisePercentage(@ApiIgnore Principal principal){
        log.info(principal.getName());
        return new SuccessResponse(
                departmentService.getPromisePercentage(principal.getName()).toResponse()
        );
    }

    @GetMapping("/promises")//부서별 공략목록
    public SuccessResponse getPromiseList(@ApiIgnore Principal principal,@RequestParam Long departmentId){
        log.info("{} : 부서별 공약조회",principal.getName());
        return new SuccessResponse(
                departmentService.getPromises(principal.getName(), departmentId)
        );
    }


    @GetMapping("")//부서목록
    public SuccessResponse getDepartmentLists(@ApiIgnore Principal principal){
        log.info("{} : 부서목록 조회",principal.getName());
        return new SuccessResponse(
                departmentService.getDepartmentLists(principal.getName()).stream()
                        .map(departmentInfoResponseDto -> departmentInfoResponseDto.toResponse())
                        .collect(Collectors.toList())
        );
    }

}
