package com.sejong.creativesemester.login.dto.sejong;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class SejongMemberRequestDto {

    private String id;

    private String pw;


    @Builder
    public SejongMemberRequestDto(String id, String pw){
        this.id = id;
        this.pw = pw;
    }
}
