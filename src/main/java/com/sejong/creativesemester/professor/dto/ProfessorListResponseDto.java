package com.sejong.creativesemester.professor.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class ProfessorListResponseDto {

    private int totalPage;
    private int currentPage;
    private List<ProfessorSimpleResponseDto> list;
}
