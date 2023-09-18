package com.sejong.creativesemester.vote.controller.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VoteCountResponse {
    private int agreeCnt;
    private int opposeCnt;
}
