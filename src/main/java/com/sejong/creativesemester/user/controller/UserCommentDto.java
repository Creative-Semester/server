package com.sejong.creativesemester.user.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserCommentDto {
    private Long id;
    private String text;
    @JsonFormat(pattern = "YYYY-MM-dd hh:mm:ss")
    private LocalDateTime createdTime;
    private Long boardId;
}
