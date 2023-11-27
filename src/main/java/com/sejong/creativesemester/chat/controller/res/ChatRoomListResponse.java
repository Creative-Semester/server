package com.sejong.creativesemester.chat.controller.res;

import com.sejong.creativesemester.chat.controller.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Schema(description = "채팅방 목록정보")
@Getter
@Builder
@AllArgsConstructor
public class ChatRoomListResponse {
    @Schema(description = "참여중인 채팅방 정보 목록 리스트")
    private List<ChatRoomInfoResponse> chatRoomDetailInfoResponseList;

    @Schema(description = "전체 페이지 번호")
    private int totalPages;

    @Schema(description = "현재 페이지 번호")
    private int currentPage;
}
