package com.sejong.creativesemester.comment.repository.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.DatabindException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Schema(description = "댓글 응답값")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentListDto {
    @Schema(description = "댓글id")
    private Long id;
    @Schema(description = "댓글내용")
    private String text;
    @Schema(description = "댓글 생성시간")
    @JsonFormat(pattern = "YYYY-MM-DD hh:mm")
    private LocalDateTime createdTime;
    @Schema(description = "댓글 작성자 학번")
    private String studentNum;
}
