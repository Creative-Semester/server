package com.sejong.creativesemester.user.controller;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserCommentDto {
    private Long id;
    private String text;

    private LocalDateTime createdTime;
    private Long boardId;
}
