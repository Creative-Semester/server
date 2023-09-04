package com.sejong.creativesemester.board.dto;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
public class BoardListResponseDto {

    private int totalPages;
    private int currentPage;
    private List<BoardSimpleResponseDto> boards;

}