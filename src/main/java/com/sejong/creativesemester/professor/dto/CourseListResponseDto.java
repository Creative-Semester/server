package com.sejong.creativesemester.professor.dto;

import com.sejong.creativesemester.professor.entity.Professor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class CourseListResponseDto {

    private int totalPage;
    private int currentPage;

    private List<CourseSimpleResponseDto> list;
    private ProfessorSimpleResponseDto professorSimpleResponseDto;
}
