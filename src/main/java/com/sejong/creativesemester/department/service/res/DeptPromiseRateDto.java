package com.sejong.creativesemester.department.service.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeptPromiseRateDto {
    @Schema(description = "부서명")
    private String departmentName;
    @Schema(description = "공약 이행률")
    private Double percent;
}
