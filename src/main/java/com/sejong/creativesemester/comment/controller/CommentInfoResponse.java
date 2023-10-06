package com.sejong.creativesemester.comment.controller;

import com.sejong.creativesemester.comment.service.res.CommentInfoDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CommentInfoResponse {
    private int totalPage;
    private int currentPage;
    private List<CommentInfoDto> commentList;
}
