package com.sejong.creativesemester.vote.controller;

import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.vote.controller.res.VoteCountResponse;
import com.sejong.creativesemester.vote.service.VoteService;
import com.sejong.creativesemester.vote.service.res.VoteCountResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class VoteController {
    private final VoteService voteService;
    @PostMapping("/boards/{boardId}/vote")
    public SuccessResponse voteAdd(@PathVariable Long boardId,
                                   @RequestParam String studentNum,@RequestParam String type){
        voteService.vote(studentNum,boardId, type);
        return SuccessResponse.ok();
    }
    @GetMapping("/boards/{boardId}/vote")
    public SuccessResponse getVoteCnt(@PathVariable Long boardId){
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