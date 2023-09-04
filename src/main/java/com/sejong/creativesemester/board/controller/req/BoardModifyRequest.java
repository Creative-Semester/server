package com.sejong.creativesemester.board.controller.req;

import lombok.Getter;

@Getter
public class BoardModifyRequest {
    private String title;
    private String content;
    private String image;
}
