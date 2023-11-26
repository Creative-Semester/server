package com.sejong.creativesemester.note.controller.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "채팅 보낸후 응답값")
@Builder
@Getter
public class SendNoteResponse {
    @Schema(description = "채팅 내용")
    private String content;
    @Schema(description = "채팅 보낸 사람 학번")
    private String senderStudentNum;
    @Schema(description = "채팅을 받은 사람 학번")
    private String receiverStudentNum;
}
