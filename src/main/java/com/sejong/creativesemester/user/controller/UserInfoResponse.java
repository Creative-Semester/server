package com.sejong.creativesemester.user.controller;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserInfoResponse {
    private String name;
    private String majorName;
}
