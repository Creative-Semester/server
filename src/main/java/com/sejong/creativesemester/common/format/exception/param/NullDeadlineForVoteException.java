package com.sejong.creativesemester.common.format.exception.param;

import com.sejong.creativesemester.common.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;

import static com.sejong.creativesemester.common.format.exception.param.errorEnum.ParamErrorCode.NULL_DEADLINE_FOR_VOTE;

public class NullDeadlineForVoteException extends ApplicationRunException{
    private static final ErrorEnumCode CODE = NULL_DEADLINE_FOR_VOTE;

    public NullDeadlineForVoteException() {
        this(CODE);
    }

    public NullDeadlineForVoteException(ErrorEnumCode errorEnumCode) {
        super(errorEnumCode);
    }
}
