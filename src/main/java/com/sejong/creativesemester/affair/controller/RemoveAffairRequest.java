package com.sejong.creativesemester.affair.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class RemoveAffairRequest {
    @Schema(name = "사무내역 파일 이름")
    private String affairName;
}
