package com.sejong.creativesemester.comment.service;

import com.sejong.creativesemester.comment.controller.CommentInfoResponse;
import com.sejong.creativesemester.comment.service.res.CommentInfoDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CommentInfoResponseDto {
    private int totalPage;
    private int currentPage;
    private List<CommentInfoDto> commentInfoDtoList;
    public CommentInfoResponse toResponse(){
        return CommentInfoResponse.builder()
                .commentInfoDtoList(commentInfoDtoList)
                .totalPage(totalPage)
                .currentPage(currentPage)
                .build();
    }
}
