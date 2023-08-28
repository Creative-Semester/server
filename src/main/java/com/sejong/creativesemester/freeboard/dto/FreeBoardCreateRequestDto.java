package com.sejong.creativesemester.freeboard.dto;

import com.sejong.creativesemester.freeboard.entity.FreeBoard;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeBoardCreateRequestDto {

    String title;
    String content;
    String image;

    public FreeBoard toEntity(){
        return FreeBoard.builder()
                .title(title)
                .content(content)
                .image(image).build();
    }

}