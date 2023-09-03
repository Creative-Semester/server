package com.sejong.creativesemester.common.format.exception.board.errorCode;

import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BoardErrorCode implements ErrorEnumCode {
    Not_Found_BOARD("B001","해당하는 글이 존재하지 않습니다.");
    private final String code;
    private final String message;
}
