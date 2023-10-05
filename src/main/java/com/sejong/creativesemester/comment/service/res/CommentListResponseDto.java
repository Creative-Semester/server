package com.sejong.creativesemester.comment.service.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Schema(description = "댓글 응답값")
@Getter
@Builder
public class CommentListResponseDto {
    @Schema(description = "댓글id")
    private Long id;
    @Schema(description = "댓글내용")
    private String text;
    @Schema(description = "댓글 생성시간")
    private LocalDateTime createdTime;
    @Schema(description = "댓글 작성자 학번")
    private Boolean isMine;

}
