package com.sejong.creativesemester.login.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SejongMemberResponseBody {

    private String grade;
    private String major;
    private String name;
    private String status;
}
