package com.sejong.creativesemester.professor.dto;

import com.sejong.creativesemester.file.service.dto.res.ImageInfoResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.awt.*;

@Builder
@Getter
public class ProfessorListSimpleResponseDto {

    private Long professorId;
    private String name;
    private String intro;
    private ImageInfoResponseDto file;
}
