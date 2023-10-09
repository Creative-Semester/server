package com.sejong.creativesemester.department.controller.res;

import com.sejong.creativesemester.department.service.res.DeptPromiseRateDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Schema(description = "공약 이행률 응답값")
@Builder
@Getter
public class PromisePercentageResponse {
    @Schema(description = "각 부서별 공약이행률")
    private List<DeptPromiseRateDto> deptPromiseRate;
    @Schema(description = "전체 공약에서의 이행률")
    private Double totalPercent;
}
