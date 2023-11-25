package com.sejong.creativesemester.chat.controller.res;

import com.sejong.creativesemester.chat.controller.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MessageListInfoResponse {
    private List<MessageResponse> messageResponseList;
    private PageInfo pageInfo;
}
