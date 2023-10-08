package com.sejong.creativesemester.department.service.res;

import com.sejong.creativesemester.department.controller.res.PromisePercentageResponse;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Schema(description = "학생회 부서별 공략 이행률 응답값")
@AllArgsConstructor
@Builder
@Getter
public class PromisePercentageResponseDto {

    //부서명과 이행률나와서 그걸 리스트로
    @ApiModelProperty(value = "공약별 달성률")
    private List<DeptPromiseRateDto> deptPromiseRateDtos;
    @ApiModelProperty(value = "전체 이행률")
    private Double totalPercent;

    public PromisePercentageResponse toResponse(){
        return PromisePercentageResponse.builder()
                .deptPromiseRate(deptPromiseRateDtos)
                .totalPercent(totalPercent)
                .build();
    }

}
