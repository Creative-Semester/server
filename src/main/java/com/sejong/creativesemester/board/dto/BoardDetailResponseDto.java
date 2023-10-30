package com.sejong.creativesemester.board.dto;

import com.sejong.creativesemester.file.service.dto.res.ImageInfoResponseDto;
import com.sejong.creativesemester.vote.service.res.VoteDetail;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Schema(description = "게시글 상세 정보 응답결과")
@Getter
@Builder
public class BoardDetailResponseDto {

    @Schema(description = "게시글 제목")
    private String title;
    @Schema(description = "게시글 내용")
    private String content;
    @Schema(description = "이미지 정보 리스트")
    private List<ImageInfoResponseDto> images;
    @Schema(description = "게시글을 조회한 사용자가 작성한 게시글이 맞는지 확인하는 bool형")
    private Boolean isMine;
    @Schema(description = "투표 정보, 투표가 없는 글이라면 null")
    private VoteDetail voteDetail;
}