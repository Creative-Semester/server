package com.sejong.creativesemester.council.service.res;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "학생회 정보 조회 응답값")
@Builder
@AllArgsConstructor
@Getter
public class CouncilInfoResponse {
    @ApiModelProperty(value = "학생회명")
    private String name;
    @ApiModelProperty(value = "학생회 소개글")
    private String introduce;
    @ApiModelProperty(value = "몇대 학생회인지")
    private Integer number;
}