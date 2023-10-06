package com.sejong.creativesemester.comment.controller;

import com.sejong.creativesemester.comment.controller.req.AddCommentRequest;
import com.sejong.creativesemester.comment.controller.res.CommentListResponse;
import com.sejong.creativesemester.comment.service.CommentInfoResponseDto;
import com.sejong.creativesemester.comment.service.CommentService;
import com.sejong.creativesemester.comment.service.res.CommentInfoDto;
import com.sejong.creativesemester.common.format.success.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/boards")
public class BoardCommentController {
    private final CommentService commentService;

    @ApiOperation(value = "게시글 댓글생성 api",
            notes = "수업게시판의 경우 따로 api 만들예정")
    @PostMapping("/{boardId}/comment")
    public SuccessResponse addComment(
            @ApiIgnore Principal principal
            , @Parameter(name = "boardId", description = "게시글의 번호") @PathVariable Long boardId
            , @Valid @RequestBody AddCommentRequest addCommentRequest) {
        commentService.addComment(boardId, principal.getName(), addCommentRequest.toRequestDto());
        return SuccessResponse.ok("댓글이 작성되었습니다.");
    }


    @ApiOperation(value = "게시글 댓글 조회 api",
            notes = "수업게시판의 경우 따로 댓글 조회 api 존재")
    @GetMapping("/{boardId}/comment")
    public SuccessResponse<CommentInfoResponse> getCommentList(@ApiIgnore Principal principal
            , @Parameter(name = "boardId", description = "댓글을 달고자하는 게시글의 id") @PathVariable Long boardId
            , @Parameter(name = "page", description = "댓글보기를 원하는 페이지 번호") @RequestParam int page) {
        return new SuccessResponse<>(commentService.getCommentList(principal.getName(), boardId, page).toResponse());
    }

}
