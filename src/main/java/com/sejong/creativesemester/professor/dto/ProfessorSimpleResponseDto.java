package com.sejong.creativesemester.professor.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfessorSimpleResponseDto {

    private String name;

    private String image;
    private String location;
    private String phoneNum;
    private String email;
    private String majorSub;
    private String lab;
}
