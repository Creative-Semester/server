package com.sejong.creativesemester.chat.controller.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "채팅방 상세정보 응답dto")
@Getter
@Builder
public class ChatRoomDetailInfoResponse {
    @Schema(description = "채팅방id")
    private long roomId;
    @Schema(description = "채팅 전송하는 사람 학번")
    private String senderStudentNum;
    @Schema(description = "게시글 작성자 학번(== 채팅 받는 사람 학번)")
    private String receiverStudentNum;
}
