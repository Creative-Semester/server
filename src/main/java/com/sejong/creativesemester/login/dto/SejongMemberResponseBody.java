package com.sejong.creativesemester.login.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@NoArgsConstructor
@Getter
public class SejongMemberResponseBody {

    private String grade;
    private String major;
    private String name;
    private String status;
}
