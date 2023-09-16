package com.sejong.creativesemester.board.dto;

import com.sejong.creativesemester.board.entity.Board;
import com.sejong.creativesemester.vote.entity.Vote;
import com.sejong.creativesemester.vote.service.res.VoteDetail;
import io.swagger.annotations.Info;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardDetailResponseDto {
    private String title;
    private String content;
    private String image;
    private Boolean isMine;
    private VoteDetail voteDetail;
}