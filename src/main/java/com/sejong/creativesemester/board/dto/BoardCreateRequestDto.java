package com.sejong.creativesemester.board.dto;

import com.sejong.creativesemester.board.entity.Board;
import com.sejong.creativesemester.vote.entity.Vote;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Data
public class BoardCreateRequestDto {
    @ApiModelProperty(value = "게시글 제목")
    private String title;
    @ApiModelProperty(value = "게시글 내용")
    private String content;
    @ApiModelProperty(value = "이미지 url")
    private String image;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .image(image).build();
    }

}