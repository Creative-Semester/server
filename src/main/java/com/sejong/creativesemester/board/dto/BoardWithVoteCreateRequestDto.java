package com.sejong.creativesemester.board.dto;

import com.sejong.creativesemester.board.controller.req.ImageInfoRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class BoardWithVoteCreateRequestDto {
    @ApiModelProperty(value = "게시글 제목")
    private String title;
    @ApiModelProperty(value = "게시글 내용")
    private String content;
    @ApiModelProperty(value = "이미지 url")
    private List<ImageInfoRequest> image;

    @ApiModelProperty(value = "마감기한",notes = "투표할때만 필요")
    private LocalDateTime deadLine;
}