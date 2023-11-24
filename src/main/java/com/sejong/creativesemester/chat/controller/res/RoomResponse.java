package com.sejong.creativesemester.chat.controller.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomResponse {
    private long roomId;
    private String senderStudentNum;
    private String receiverStudentNum;
}
