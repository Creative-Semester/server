package com.sejong.creativesemester.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sejong.creativesemester.image.entity.Image;
import com.sejong.creativesemester.image.service.dto.res.ImageInfoResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.annotation.security.DenyAll;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Schema(description = "게시글 조회시 필요한 간단한 게시글 정보")
@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter
public class BoardSimpleResponseDto {

    @Schema(description = "게시글 id")
    private Long boardId;
    @Schema(description = "게시글 제목")
    private String title;
    @Schema(description = "게시글")
    private String content;
    @Schema(description = "이미지 정보")
    private List<ImageInfoResponseDto> images;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")

    @Schema(description = "게시글 생성 시간")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;
}
