package com.sejong.creativesemester.common.format.exception.chatroom.errorCode;

import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ChatRoomErrorCode implements ErrorEnumCode {
    NOT_FOUND_CHATROOM("CR001","해당 채팅방을 찾을수 없습니다.");
    private final String code;
    private final String message;
}
