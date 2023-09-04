package com.sejong.creativesemester.common.format.exception.board;

import com.sejong.creativesemester.common.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;

import static com.sejong.creativesemester.common.format.exception.board.errorCode.BoardErrorCode.NOT_FOUND_BOARD;

public class NotFoundBoardException extends ApplicationRunException {
    private static final ErrorEnumCode CODE = NOT_FOUND_BOARD;
    private NotFoundBoardException(ErrorEnumCode CODE){
        super(CODE);
    }
    public NotFoundBoardException() {
        this(CODE);
    }
}