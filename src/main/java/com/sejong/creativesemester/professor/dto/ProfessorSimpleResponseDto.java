package com.sejong.creativesemester.professor.dto;

import lombok.Builder;

@Builder
public class ProfessorSimpleResponseDto {

    private Long boardId;
    private String name;
    private String intro;
    private String image;
}
