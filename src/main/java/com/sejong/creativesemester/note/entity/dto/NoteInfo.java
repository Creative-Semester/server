package com.sejong.creativesemester.note.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class NoteInfo {
    @Schema(description = "채팅내용")
    private String contents;
    @Schema(description = "채팅 작성 시각")
    @JsonFormat(pattern = "YYYY-MM-dd hh:mm:ss")
    private LocalDateTime sendTime;
    @Schema(description = "채팅을 보낸 사람 학번")
    private String senderStudentNum;
}
