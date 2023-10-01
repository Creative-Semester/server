package com.sejong.creativesemester.board.dto;

import com.sejong.creativesemester.image.service.dto.res.ImageInfoResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@Builder
public class BoardSimpleResponseDto {
    private Long boardId;
    private String title;
    private String content;
    private ImageInfoResponseDto images;
}
