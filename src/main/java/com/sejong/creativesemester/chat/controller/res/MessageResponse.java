package com.sejong.creativesemester.chat.controller.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MessageResponse { // 채팅방 속 하나의 메세지
    private long messageId;
    private String senderStudentNum;
    private String content;
    private LocalDateTime sendTime;
}
