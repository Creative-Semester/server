package com.sejong.creativesemester.common.format.exception.vote;

import com.sejong.creativesemester.common.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;

import static com.sejong.creativesemester.common.format.exception.vote.errorCode.VoteErrorCode.NOT_FOUND_VOTE;

public class NotFoundVoteException extends ApplicationRunException {
    private static final ErrorEnumCode CODE = NOT_FOUND_VOTE;
    public NotFoundVoteException(){
        this(CODE);
    }
    private NotFoundVoteException(ErrorEnumCode CODE){
        super(CODE);
    }
}
