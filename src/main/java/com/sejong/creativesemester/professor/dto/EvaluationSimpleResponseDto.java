package com.sejong.creativesemester.professor.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class EvaluationSimpleResponseDto {

    private Long evaluationId;
    private String studentNum;
    private String text;
    private LocalDateTime createdTime;
}
