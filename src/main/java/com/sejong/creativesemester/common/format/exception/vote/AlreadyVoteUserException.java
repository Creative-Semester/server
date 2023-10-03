package com.sejong.creativesemester.common.format.exception.vote;

import com.sejong.creativesemester.common.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;
import com.sejong.creativesemester.common.format.exception.vote.errorCode.VoteErrorCode;

public class AlreadyVoteUserException extends ApplicationRunException {
    private static final ErrorEnumCode CODE = VoteErrorCode.ALREADY_VOTE_USER;
    public AlreadyVoteUserException(){
        this(CODE);
    }
    private AlreadyVoteUserException(ErrorEnumCode CODE){
        super(CODE);
    }
}
