package com.sejong.creativesemester.board.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class BoardSimpleResponseDto {
    private Long boardId;
    private String title;
    private String content;
}
