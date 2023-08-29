package com.sejong.creativesemester.department.service.res;

import com.sejong.creativesemester.department.controller.res.PromisePercentageResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Builder
@Getter
public class PromisePercentageResponseDto {
    private Map<String,Double> promisePercentage;

    public PromisePercentageResponse toResponse(){
        return PromisePercentageResponse.builder()
                .promisePercentage(promisePercentage)
                .build();
    }

}
