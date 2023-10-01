package com.sejong.creativesemester.board.dto;

import com.sejong.creativesemester.board.entity.Board;
import com.sejong.creativesemester.image.service.dto.res.ImageInfoResponseDto;
import com.sejong.creativesemester.vote.entity.Vote;
import com.sejong.creativesemester.vote.service.res.VoteDetail;
import io.swagger.annotations.Info;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BoardDetailResponseDto {
    private String title;
    private String content;
    private List<ImageInfoResponseDto> images;
    private Boolean isMine;
    private VoteDetail voteDetail;
}