package com.sejong.creativesemester.user.controller;

import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.user.service.UserBoardResponseDto;
import com.sejong.creativesemester.user.service.UserInfoResponseDto;
import com.sejong.creativesemester.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/info")
    public SuccessResponse<UserInfoResponse> userInfo(@ApiIgnore Authentication authentication){
        return new SuccessResponse(userService.getMyInfo(authentication.getName()).toResponse());
    }

    @GetMapping("/boards")
    public SuccessResponse<UserBoardResponse> myBoard(@ApiIgnore Authentication authentication
            , @RequestParam int page) {
        UserBoardResponseDto myBoards = userService.getMyBoards(authentication.getName(), page);

        return new SuccessResponse(UserBoardResponse.fromReponseDto(myBoards));
    }

    @GetMapping("/comment")
    public SuccessResponse<UserCommentReponse> myComment(@ApiIgnore Authentication authentication
            , @RequestParam int page) {
        Page<UserCommentResponseDto> myComment = userService.getMyComment(authentication.getName(), page);
        return new SuccessResponse(UserCommentReponse.builder()
                .totalPage(myComment.getTotalPages())
                .currentPage(myComment.getNumber())
                .comment(myComment.stream().map(userCommentResponseDto -> UserCommentDto.builder()
                        .id(userCommentResponseDto.getCommentId())
                        .createdTime(userCommentResponseDto.getCreatedTime())
                        .text(userCommentResponseDto.getText())
                        .boardId(userCommentResponseDto.getBoardId())
                        .build()).collect(Collectors.toList()))
                .build());
    }

}
