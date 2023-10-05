package com.sejong.creativesemester.department.controller.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.swing.*;

@Schema(description = "학생회 내의 부서목록 조회 응답값")
@Getter
@Builder
public class DepartmentInfoResponse {
    @Schema(description = "학생회 부서id")
    private Long id;
    @Schema(description = "학생회 부서명")
    private String name;
}
