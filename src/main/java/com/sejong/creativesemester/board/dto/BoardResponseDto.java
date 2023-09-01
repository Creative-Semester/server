package com.sejong.creativesemester.board.dto;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
public class BoardResponseDto {

    private long totalElements; // 총 게시글 수
    private int totalPage; // 총 페이지 수
    private List<BoardDetailResponseDto> boardList; // 페이지 당 들어가는 게시글 리스트

    public static BoardResponseDto dto(Page<BoardDetailResponseDto> page){
        return new BoardResponseDto(page.getTotalElements(), page.getTotalPages(), page.getContent());
    }

}