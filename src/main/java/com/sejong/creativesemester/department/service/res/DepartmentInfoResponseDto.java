package com.sejong.creativesemester.department.service.res;

import com.sejong.creativesemester.department.controller.res.DepartmentInfoResponse;
import com.sejong.creativesemester.department.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentInfoResponseDto {
    private Long id;
    private String name;
    public DepartmentInfoResponse toResponse(){
        return DepartmentInfoResponse.builder()
                .id(id)
                .name(name)
                .build();
    }
}
