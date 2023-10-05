package com.sejong.creativesemester.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Schema(
        description = "게시글 목록 조회반환되는 응답결과"
)
@Getter
@Builder
public class BoardListResponseDto {

    @Schema(description = "전체 페이지 번호")
    private int totalPages;

    @Schema(description = "현재 페이지 번호")
    private int currentPage;

    @Schema(description = "게시글 조회시 필요한 간단한 게시글 정보 리스트")
    private List<BoardSimpleResponseDto> boards;

}