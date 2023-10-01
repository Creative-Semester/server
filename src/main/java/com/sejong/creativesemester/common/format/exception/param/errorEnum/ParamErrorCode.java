package com.sejong.creativesemester.common.format.exception.param.errorEnum;

import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParamErrorCode implements ErrorEnumCode {
    NOT_MATCH_CONDITION("P001", "파라미터를 다시 확인해주세요"),
    NULL_DEADLINE_FOR_VOTE("P002","투표 마감기한은 필수사항입니다.");
    private String code;
    private String message;
}
