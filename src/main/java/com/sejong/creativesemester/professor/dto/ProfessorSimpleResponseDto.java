package com.sejong.creativesemester.professor.dto;

import com.sejong.creativesemester.file.service.dto.res.ImageInfoResponseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfessorSimpleResponseDto {

    private String name;

    private ImageInfoResponseDto file;
    private String location;
    private String phoneNum;
    private String email;
    private String majorSub;
    private String lab;
}
