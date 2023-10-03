package com.sejong.creativesemester.common.config.format.exception.user.errorCode;

import com.sejong.creativesemester.common.config.format.exception.ErrorEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorEnumCode {
    NOT_FOUND_USER("U001", "사용자를 찾지 못했습니다"),
    NOT_HAVE_ROLE("U002","해당 게시판에 대한 권한을 가지고 있지 않습니다.");
    private final String code;
    private final String message;
}
