package com.sejong.creativesemester.department.service.res;

import com.sejong.creativesemester.department.controller.res.DepartmentInfoResponse;
import com.sejong.creativesemester.department.entity.Department;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "학생회 내의 부서목록 조회dto")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentInfoResponseDto {
    @Schema(description = "학생회 부서id")
    private Long id;
    @Schema(description = "학생회 부서명")
    private String name;
    public DepartmentInfoResponse toResponse(){
        return DepartmentInfoResponse.builder()
                .id(id)
                .name(name)
                .build();
    }
}
