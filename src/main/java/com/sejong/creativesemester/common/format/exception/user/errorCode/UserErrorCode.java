package com.sejong.creativesemester.common.format.exception.user.errorCode;

import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorEnumCode {
    NOT_FOUND_USER("U001", "사용자를 찾지 못했습니다");
    private final String code;
    private final String message;
}
