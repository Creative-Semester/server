package com.sejong.creativesemester.professor.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProfessorListSimpleResponseDto {

    private Long professId;
    private String name;
    private String intro;
    private String image;
}
