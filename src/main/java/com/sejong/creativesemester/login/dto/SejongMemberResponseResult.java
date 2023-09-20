package com.sejong.creativesemester.login.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SejongMemberResponseResult {

    private SejongMemberResponseBody body;
    private boolean is_auth;
}
