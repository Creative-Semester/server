package com.sejong.creativesemester.common.config.format.exception.board.errorCode;

import com.sejong.creativesemester.common.config.format.exception.ErrorEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BoardErrorCode implements ErrorEnumCode {
    NOT_FOUND_BOARD("B001","해당하는 글이 존재하지 않습니다."),
    NOT_MATCH_USER("B002","일치하지 않는 사용자입니다."),;
    private final String code;
    private final String message;
}
