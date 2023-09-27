package com.sejong.creativesemester.login.domain;

import com.sejong.creativesemester.user.entity.Role;
import lombok.*;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity(name = "REFRESH_TOKEN")
public class RefreshToken {

    @Id
    private String studentNum;

    private String userName;
    private Role role;

    private String ip;

    @Indexed
    private String refreshToken;

}
