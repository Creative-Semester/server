package com.sejong.creativesemester.vote.service.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Schema(description = "투표 정보")
@Builder
@Getter
public class VoteDetail {
    @Schema(description = "투표id")
    private Long voteId;
    @Schema(description = "찬성 개수")
    private Integer agreeCnt;
    @Schema(description = "반대 개수")
    private Integer opposeCnt;
    @Schema(description = "마감 기한")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deadLine;
}
