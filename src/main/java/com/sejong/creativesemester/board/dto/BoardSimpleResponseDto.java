package com.sejong.creativesemester.board.dto;

import com.sejong.creativesemester.image.service.dto.res.ImageInfoResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


@Schema(description = "게시글 조회시 필요한 간단한 게시글 정보")
@Getter
@Builder
public class BoardSimpleResponseDto {

    @Schema(description = "게시글 id")
    private Long boardId;
    @Schema(description = "게시글 제목")
    private String title;
    @Schema(description = "게시글")
    private String content;
    @Schema(description = "이미지 정보")
    private ImageInfoResponseDto images;
    private LocalDateTime day;
}
