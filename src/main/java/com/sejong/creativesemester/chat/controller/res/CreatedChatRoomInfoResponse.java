package com.sejong.creativesemester.chat.controller.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "채팅방 id 응답dto")
@Getter
@Builder
public class CreatedChatRoomInfoResponse {
    @Schema(description = "채팅방ID")
    private Long roomId;
}
