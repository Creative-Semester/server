package com.sejong.creativesemester.council.service.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class CouncilInfoResponse {
    private String name;
    private String introduce;
    private Integer number;
}