package com.sejong.creativesemester.professor.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ProfessorListResponseDto {

    private int totalPage;
    private int currentPage;
    private List<ProfessorListSimpleResponseDto> list;
}
