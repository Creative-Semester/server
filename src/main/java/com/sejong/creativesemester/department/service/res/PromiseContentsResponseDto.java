package com.sejong.creativesemester.department.service.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "각 공약의 이행여부 응답값")
@Getter
@AllArgsConstructor
@Builder
public class PromiseContentsResponseDto {
    @Schema(description = "공약내용")
    private String contents;
    @Schema(description = "이행여부")
    private boolean implementation;
}
