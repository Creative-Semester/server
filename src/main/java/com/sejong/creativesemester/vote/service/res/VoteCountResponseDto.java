package com.sejong.creativesemester.vote.service.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VoteCountResponseDto {
    private int agreeCnt;
    private int opposeCnt;
}
