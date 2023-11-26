package com.sejong.creativesemester.chat.controller.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Schema(defaultValue = "채팅방Id를 생성 및 조회하는 api")
@Getter
public class PostRequest {
    @NotNull
    @Schema(defaultValue = "채팅을 보낼 게시판 id")
    private Long boardId; // 채팅 보낼 게시판
}
