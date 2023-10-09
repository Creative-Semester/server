package com.sejong.creativesemester.professor.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class EvaluationSimpleResponseDto {

    private String name;
    private String text;
    private LocalDateTime createdTime;
}
