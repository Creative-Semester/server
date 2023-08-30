package com.sejong.creativesemester.freeboard.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FreeBoardDetailResponseDto {

    private String title;
    private String content;
    private String image;
}