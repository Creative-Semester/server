package com.sejong.creativesemester.affair.controller;

import lombok.Getter;

import java.util.List;

@Getter
public class SaveAffairRequest {
    private String title;
    private String restMoney;
    private String usedMoney;
    private AffairFileInfoRequest affairFiles;
}
