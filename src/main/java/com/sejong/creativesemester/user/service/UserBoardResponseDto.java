package com.sejong.creativesemester.user.service;

import com.sejong.creativesemester.board.entity.BoardType;
import com.sejong.creativesemester.image.service.dto.res.ImageInfoResponseDto;
import lombok.*;

import java.util.ArrayList;
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
