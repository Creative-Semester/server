package com.sejong.creativesemester.department.controller.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.swing.*;
import java.util.Map;

@Schema(description = "공약 이행률 응답값")
@Builder
@Getter
public class PromisePercentageResponse {
    @Schema(description = "각 부서별 공약이행률")
    private Map<String,Double> promisePercentage;
    @Schema(description = "전체 공약에서의 이행률")
    private Double totalPercent;
}
