package com.sejong.creativesemester.comment.controller;

import com.sejong.creativesemester.comment.controller.req.AddCommentRequest;
import com.sejong.creativesemester.comment.service.CommentService;
import com.sejong.creativesemester.common.format.success.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/boards")
public class BoardCommentController {
    private final CommentService commentService;

    @ApiOperation(value = "게시글 댓글생성 api",
            notes = "수업게시판의 경우 따로 api 만들예정")
    @PostMapping("/{boardId}/comment")
    public SuccessResponse addComment(/*@ApiIgnore Principal principal*/@RequestParam String studentNum, @PathVariable Long boardId
            , @RequestBody AddCommentRequest addCommentRequest) {
        commentService.addComment(boardId, studentNum/*principal.getName()*/, addCommentRequest.toRequestDto());
        return SuccessResponse.ok("댓글이 작성되었습니다.");
    }


    @ApiOperation(value = "게시글 댓글 조회 api",
            notes = "수업게시판의 경우 따로 댓글 조회 api 존재")
    @GetMapping("/{boardId}/comment")
    public SuccessResponse getCommentList(@PathVariable Long boardId) {
        return new SuccessResponse(commentService.getCommentList(boardId));
    }

}
