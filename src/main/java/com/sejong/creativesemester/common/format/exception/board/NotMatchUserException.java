package com.sejong.creativesemester.common.format.exception.board;

import com.sejong.creativesemester.common.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;

import static com.sejong.creativesemester.common.format.exception.board.errorCode.BoardErrorCode.NOT_MATCH_USER;

public class NotMatchUserException extends ApplicationRunException {
    private final static ErrorEnumCode CODE=NOT_MATCH_USER;

    private NotMatchUserException(ErrorEnumCode CODE){
        super(CODE);
    }

    public NotMatchUserException(){
        this(CODE);
    }
}
