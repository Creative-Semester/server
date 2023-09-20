package com.sejong.creativesemester.vote.service.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class VoteDetail {
    private Long voteId;
    private Integer agreeCnt;
    private Integer opposeCnt;
    private LocalDateTime deadLine;
}
