package com.sejong.creativesemester.department.controller;

import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.department.controller.res.PromisePercentageResponse;
import com.sejong.creativesemester.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/department")
@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/promises/percentage")
    public SuccessResponse getPromisePercentage(Principal principal){
        log.info(principal.getName());
        return new SuccessResponse(
                departmentService.getPromisePercentage(principal.getName()).toResponse()
        );
    }
}
