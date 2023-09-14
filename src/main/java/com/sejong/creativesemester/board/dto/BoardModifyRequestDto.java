package com.sejong.creativesemester.board.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardModifyRequestDto {
    @ApiModelProperty(value = "수정후의 글의 제목")
    private String title;
    @ApiModelProperty(value = "수정후의 글의 내용")
    private String content;
    @ApiModelProperty(value = "수정후의 이미지url")
    private String image;
}