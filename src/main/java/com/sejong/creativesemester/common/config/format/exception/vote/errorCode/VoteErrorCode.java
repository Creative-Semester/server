package com.sejong.creativesemester.common.config.format.exception.vote.errorCode;

import com.sejong.creativesemester.common.config.format.exception.ErrorEnumCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum VoteErrorCode implements ErrorEnumCode {
    NOT_FOUND_VOTE("V001","해당 게시물에는 투표기능이 존재하지 않습니다."),
    ALREADY_VOTE_USER("V002","이미 투표를 완료한 사용자입니다.");
    private final String code;
    private final String message;
}
