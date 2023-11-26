package com.sejong.creativesemester.note.controller.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "채팅 보낼때 DTO")
@Getter
public class SendNoteRequest {
    @Schema(description = "채팅 내용")
    private String content;
}
