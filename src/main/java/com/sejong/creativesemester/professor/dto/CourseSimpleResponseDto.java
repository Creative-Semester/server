package com.sejong.creativesemester.professor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CourseSimpleResponseDto {

    private Long courseId;

    private String title;
    private String classification;
    private String grade;
    private String score;

}
