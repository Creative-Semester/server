package com.sejong.creativesemester.common.format.exception.vote;

import com.sejong.creativesemester.common.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;
import com.sejong.creativesemester.common.format.exception.vote.errorCode.VoteErrorCode;

public class ExpiredVotePeriodException extends ApplicationRunException {
    private static final ErrorEnumCode CODE = VoteErrorCode.EXPIRED_VOTE_PERIOD;
    public ExpiredVotePeriodException(){
        this(CODE);
    }
    private ExpiredVotePeriodException(ErrorEnumCode CODE){
        super(CODE);
    }
}
