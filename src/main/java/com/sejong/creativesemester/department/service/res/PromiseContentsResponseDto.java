package com.sejong.creativesemester.department.service.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PromiseContentsResponseDto {
    private String contents;
    private boolean implementation;
}
