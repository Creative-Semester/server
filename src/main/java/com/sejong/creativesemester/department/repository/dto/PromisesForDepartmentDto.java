package com.sejong.creativesemester.department.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PromisesForDepartmentDto {
    private String departmentName;
    private Double promiseCount;
    private Double implementationCount;
}
