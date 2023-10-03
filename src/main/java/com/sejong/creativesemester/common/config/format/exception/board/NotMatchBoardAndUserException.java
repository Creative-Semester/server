package com.sejong.creativesemester.common.config.format.exception.board;

import com.sejong.creativesemester.common.config.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.config.format.exception.ErrorEnumCode;

import static com.sejong.creativesemester.common.config.format.exception.board.errorCode.BoardErrorCode.NOT_MATCH_USER;

public class NotMatchBoardAndUserException extends ApplicationRunException {
    private final static ErrorEnumCode CODE=NOT_MATCH_USER;

    private NotMatchBoardAndUserException(ErrorEnumCode CODE){
        super(CODE);
    }

    public NotMatchBoardAndUserException(){
        this(CODE);
    }
}
