package com.sejong.creativesemester.comment.controller.res;

import com.sejong.creativesemester.comment.repository.dto.CommentListDto;
import com.sejong.creativesemester.comment.service.res.CommentListResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CommentListResponse {
    private List<CommentListResponseDto> commentList;
}
