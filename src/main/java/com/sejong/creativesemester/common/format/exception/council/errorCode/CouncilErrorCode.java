package com.sejong.creativesemester.common.format.exception.council.errorCode;

import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CouncilErrorCode implements ErrorEnumCode {
    ONLY_USE_COUNCIL("C001","학생회만 사용가능한 기능입니다.");
    private String code;
    private String message;
}
