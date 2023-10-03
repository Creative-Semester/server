package com.sejong.creativesemester.common.config.format.exception.board;

import com.sejong.creativesemester.common.config.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.config.format.exception.board.errorCode.BoardErrorCode;
import com.sejong.creativesemester.common.config.format.exception.ErrorEnumCode;

public class NotFoundBoardException extends ApplicationRunException {
    private static final ErrorEnumCode CODE = BoardErrorCode.NOT_FOUND_BOARD;
    private NotFoundBoardException(ErrorEnumCode CODE){
        super(CODE);
    }
    public NotFoundBoardException() {
        this(CODE);
    }
}
