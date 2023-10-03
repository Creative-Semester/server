package com.sejong.creativesemester.common.format.exception.board;

import com.sejong.creativesemester.common.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;
import com.sejong.creativesemester.common.format.exception.board.errorCode.BoardErrorCode;

public class NotMatchBoardAndUserException extends ApplicationRunException {
    private final static ErrorEnumCode CODE= BoardErrorCode.NOT_MATCH_USER;

    private NotMatchBoardAndUserException(ErrorEnumCode CODE){
        super(CODE);
    }

    public NotMatchBoardAndUserException(){
        this(CODE);
    }
}
