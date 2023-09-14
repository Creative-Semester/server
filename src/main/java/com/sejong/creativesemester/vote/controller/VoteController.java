package com.sejong.creativesemester.vote.controller;

import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.vote.service.VoteService;
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
}