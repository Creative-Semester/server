package com.sejong.creativesemester.user.controller;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserCommentResponseDto {
    private Long commentId;
    private String text;
    private LocalDateTime createdTime;
    private Long boardId;
}