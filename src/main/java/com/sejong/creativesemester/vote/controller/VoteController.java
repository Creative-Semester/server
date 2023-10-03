package com.sejong.creativesemester.vote.controller;

import com.sejong.creativesemester.common.config.format.success.SuccessResponse;
import com.sejong.creativesemester.vote.controller.res.VoteCountResponse;
import com.sejong.creativesemester.vote.entity.VoteType;
import com.sejong.creativesemester.vote.service.VoteService;
import com.sejong.creativesemester.vote.service.res.VoteCountResponseDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class VoteController {
    private final VoteService voteService;

    @ApiOperation(value = "게시글(학생회 게시판)에 투표하는 api",
            notes = "수업게시판의 경우는 해당 api를 사용하면 안됩니다.(추가예정)"
    )
    @PostMapping("/boards/{boardId}/vote")
    public SuccessResponse voteAdd(
            @ApiIgnore Principal principal
            , @PathVariable Long boardId
            , @Parameter(name = "studentNum", description = "학생의 학번",
            schema = @Schema(
                    type = "string",
                    allowableValues = {"AGREE,OPPOSE"}),
            in = ParameterIn.QUERY)
            @RequestParam VoteType type) {
        voteService.vote(principal.getName(), boardId, type);
        return SuccessResponse.ok();
    }

    @ApiOperation(value = "자유게시판의 게시글의 투표수를 조회하는 api",
            notes = "수업게시판의 경우는 해당 api를 사용하면 안됩니다.(추가예정)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "투표수 조회성공",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class)))
    })
    @GetMapping("/boards/{boardId}/vote")
    public SuccessResponse<VoteCountResponse> getVoteCnt(@PathVariable Long boardId) {
        VoteCountResponseDto voteCnt = voteService.getVoteCnt(boardId);
        return new SuccessResponse(
                VoteCountResponse
                        .builder()
                        .agreeCnt(voteCnt.getAgreeCnt())
                        .opposeCnt(voteCnt.getOpposeCnt())
                        .build()
        );
    }

}