package com.sejong.creativesemester.comment.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentListDto {
    private Long id;
    private String text;
    private LocalDateTime createdTime;
}
