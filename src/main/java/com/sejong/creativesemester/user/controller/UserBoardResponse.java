package com.sejong.creativesemester.user.controller;

import com.sejong.creativesemester.user.service.UserBoardDto;
import com.sejong.creativesemester.user.service.UserBoardResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class UserBoardResponse {
    private int totalPage;
    private int currentPage;
    private List<UserBoardDto> boardList;

    public static UserBoardResponse fromReponseDto(UserBoardResponseDto userBoardResponseDto){
        return UserBoardResponse.builder()
                .boardList(userBoardResponseDto.getBoardDtoList())
                .totalPage(userBoardResponseDto.getTotalPage())
                .currentPage(userBoardResponseDto.getCurrentPage())
                .build();
    }
}
