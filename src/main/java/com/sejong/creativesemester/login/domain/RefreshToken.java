package com.sejong.creativesemester.login.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
@Builder
@RedisHash("refreshToken")
public class RefreshToken {

    @Id
    private String id;

    private String refreshToken;
    @TimeToLive
    private Long expiration;

    public RefreshToken(String id, String refreshToken, Long expiration){
        this.id = id;
        this.refreshToken = refreshToken;
        this.expiration = expiration;
    }
}
