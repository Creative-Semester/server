package com.sejong.creativesemester.board.dto;

import com.sejong.creativesemester.board.entity.Board;
import com.sejong.creativesemester.vote.entity.Vote;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Data
public class BoardCreateRequestDto {
    private String title;
    private String content;
    private String image;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .image(image).build();
    }

}