package com.sejong.creativesemester.user.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sejong.creativesemester.board.entity.BoardType;
import com.sejong.creativesemester.file.service.dto.res.ImageInfoResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class UserBoardDto {
    private Long boardId;
    private String title;
    private String content;
    private List<ImageInfoResponseDto> images;
    private BoardType boardType;
    @JsonFormat(pattern = "YYYY-MM-dd hh:mm")
    private LocalDateTime createdTime;
}
