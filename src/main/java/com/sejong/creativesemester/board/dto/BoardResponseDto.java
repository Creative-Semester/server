package com.sejong.creativesemester.board.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BoardResponseDto {

    private int totalPages;
    private int currentPage;
    private List<BoardDetailResponseDto> boards;

}