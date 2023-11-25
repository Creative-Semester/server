package com.sejong.creativesemester.chat.controller.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Schema(description = "메시지 정보")
@Getter
@Builder
public class MessageResponse { // 채팅방 속 하나의 메세지

    @Schema(description = "메시지 ID")
    private long messageId;
    @Schema(description = "전송자 학번")
    private String senderStudentNum;
    @Schema(description = "메시지 내용")
    private String content;
    @Schema(description = "메시지 보낸 시각")
    private LocalDateTime sendTime;
}
