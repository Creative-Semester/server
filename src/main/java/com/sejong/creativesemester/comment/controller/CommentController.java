package com.sejong.creativesemester.comment.controller;

import com.sejong.creativesemester.comment.service.CommentService;
import com.sejong.creativesemester.common.format.success.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;
    @ApiOperation(
            value = "댓글 삭제 api"
    )
    @DeleteMapping("/{commentId}")
    public SuccessResponse deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return SuccessResponse.ok("댓글이 삭제되었습니다.");
    }

    // 신고한 사람 테이블 연관관계, 사용자 조회로 이미 매핑했는데, 한번더 매핑해도 괜찮을까
    // 수정예정
    @ApiOperation(
            value = "댓글 신고 api",
            notes = "수정예정으로 사용하지말기, 동시성 고려안함"
    )
    @PostMapping("/{commentId}/report")
    public SuccessResponse reportComment(@PathVariable Long commentId){
        commentService.reportComment(commentId);
        return SuccessResponse.ok("댓글을 신고하였습니다.");
    }


}
