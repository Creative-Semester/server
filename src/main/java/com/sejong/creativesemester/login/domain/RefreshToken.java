package com.sejong.creativesemester.login.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Refresh_Token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RefreshToken {

    @Id
    @Column(name = "refresh_token_id")
    private Long id;

    private String studentNum;
    private String token;

    private RefreshToken(String studentNum, String token){
        this.studentNum = studentNum;
        this.token = token;
    }

    public static RefreshToken createRefreshToken(String studentNum, String token){
        return new RefreshToken(studentNum, token);
    }

    public void changeToken(String token){
        this.token = token;
    }
}
