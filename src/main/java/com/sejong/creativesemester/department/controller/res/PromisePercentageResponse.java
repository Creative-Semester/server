package com.sejong.creativesemester.department.controller.res;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class PromisePercentageResponse {
    private Map<String,Double> promisePercentage;
}
