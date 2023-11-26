package com.sejong.creativesemester.common.format.exception.chatroom;

import com.sejong.creativesemester.common.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;
import com.sejong.creativesemester.common.format.exception.chatroom.errorCode.ChatRoomErrorCode;

public class NotFoundChatRoomException extends ApplicationRunException {
    private static final ErrorEnumCode CODE = ChatRoomErrorCode.NOT_FOUND_CHATROOM;
    private NotFoundChatRoomException(ErrorEnumCode CODE){
        super(CODE);
    }
    public NotFoundChatRoomException() {
        this(CODE);
    }
}
