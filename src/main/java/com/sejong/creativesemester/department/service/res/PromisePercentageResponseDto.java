package com.sejong.creativesemester.department.service.res;

import com.sejong.creativesemester.department.controller.res.PromisePercentageResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Builder
@Getter
public class PromisePercentageResponseDto {

    @ApiModelProperty(value = "공약별 달성률")
    private Map<String,Double> promisePercentage;

    public PromisePercentageResponse toResponse(){
        return PromisePercentageResponse.builder()
                .promisePercentage(promisePercentage)
                .build();
    }

}
