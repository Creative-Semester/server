package com.sejong.creativesemester.department.controller.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DepartmentInfoResponse {
    private Long id;
    private String name;
}
