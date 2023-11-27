package com.sejong.creativesemester.chat.controller.res;

import com.sejong.creativesemester.note.entity.dto.NoteInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ChatRoomInfoResponse {
    @Schema(description = "채팅방id")
    private long roomId;
    @Schema(description = "채팅 전송하는 사람 학번")
    private String senderStudentNum;
    @Schema(description = "게시글 작성자 학번(== 채팅 받는 사람 학번)")
    private String receiverStudentNum;
    @Schema(description = "해당 채팅방의 마지막 채팅정보")
    private NoteInfo noteInfos;
    @Schema(description = "게시글 제목")
    private String boardName;
}
