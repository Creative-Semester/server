package com.sejong.creativesemester.common.format.exception.vote;

import com.sejong.creativesemester.common.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;
import com.sejong.creativesemester.common.format.exception.vote.errorCode.VoteErrorCode;

public class NotFoundVoteException extends ApplicationRunException {
    private static final ErrorEnumCode CODE = VoteErrorCode.NOT_FOUND_VOTE;
    public NotFoundVoteException(){
        this(CODE);
    }
    private NotFoundVoteException(ErrorEnumCode CODE){
        super(CODE);
    }
}
