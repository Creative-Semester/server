package com.sejong.creativesemester.user.service;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserBoardResponseDto {
    private int totalPage;
    private int currentPage;
    private List<UserBoardDto> boardDtoList;
}
