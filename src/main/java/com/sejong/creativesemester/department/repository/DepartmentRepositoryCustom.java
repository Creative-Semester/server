package com.sejong.creativesemester.department.repository;

import com.sejong.creativesemester.department.repository.dto.PromisesForDepartmentDto;

import java.util.List;

public interface DepartmentRepositoryCustom {
    /*
    - 부서명
    - 각 부서의 전체 공약 개수
    - 각 부서의 공약 이행률
     */
     List<PromisesForDepartmentDto> getPromisesForDepartment(String majorName);
}
