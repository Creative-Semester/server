package com.sejong.creativesemester.vote.controller.res;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class VoteCountResponse {
    @ApiModelProperty(value = "찬성표 개수")
    private int agreeCnt;
    @ApiModelProperty(value = "반대표 개수")
    private int opposeCnt;
}
