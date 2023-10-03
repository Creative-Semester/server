package com.sejong.creativesemester.login.dto.sejong;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SejongMemberResponseResult {

    private SejongMemberResponseBody body;
    private String is_auth;
}
