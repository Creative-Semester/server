package com.sejong.creativesemester.note.service.req;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SendNoteRequestDto {
    private Long chatRoomId;
    private String senderStudentNum;
    private String content;
}
