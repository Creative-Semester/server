package com.sejong.creativesemester.common.format.exception.council.errorCode;

import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CouncilErrorCode implements ErrorEnumCode {
    ONLY_USE_COUNCIL("C001","학생회만 사용가능한 기능입니다."),
    NOT_FOUND_COUNCILCODE("C002", "코드가 일치하지 않습니다."),
    NO_MATCH_COUNCILCODE("C003", "해당 학과의 코드와 일치하지 않습니다.");

    private String code;
    private String message;
}
