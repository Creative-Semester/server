package com.sejong.creativesemester.login.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class SejongMemberResponseResult {

    private SejongMemberResponseBody body;
    private boolean is_auth;
}
