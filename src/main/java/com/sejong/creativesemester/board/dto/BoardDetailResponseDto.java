package com.sejong.creativesemester.board.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardDetailResponseDto {

    private String title;
    private String content;
    private String image;
}