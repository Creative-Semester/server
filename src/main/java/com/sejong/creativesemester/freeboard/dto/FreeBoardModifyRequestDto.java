package com.sejong.creativesemester.freeboard.dto;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeBoardModifyRequestDto {

    private Long freeBoardId;
    private String title;
    private String content;
    private String image;

}