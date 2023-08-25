package com.sejong.creativesemester.freeboard.dto;

import com.sejong.creativesemester.freeboard.entity.FreeBoard;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeBoardModifyRequestDto {

    private Long freeBoardId;
    private String title;
    private String content;
    private String image;

}
