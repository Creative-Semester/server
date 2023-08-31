package com.sejong.creativesemester.board.dto;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardModifyRequestDto {

    private Long boardId;
    private String title;
    private String content;
    private String image;

}