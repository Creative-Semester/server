package com.sejong.creativesemester.chat.controller.req;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class PostRequest {
    @NotNull
    private Long boardId; // 채팅 보낼 게시판
}
