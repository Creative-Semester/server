package com.sejong.creativesemester.board.dto;

import com.sejong.creativesemester.board.entity.Board;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class BoardCreateRequestDto {

    String title;
    String content;
    String image;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .image(image).build();
    }

}