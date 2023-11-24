package com.sejong.creativesemester.chat.controller.res;

import com.sejong.creativesemester.chat.controller.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ChatRoomResponse {
    private List<RoomResponse> roomResponseList;
    private PageInfo pageInfo;
}
