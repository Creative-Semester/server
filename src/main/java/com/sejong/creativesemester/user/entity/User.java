package com.sejong.creativesemester.user.entity;


import com.sejong.creativesemester.common.domain.BaseTimeEntity;
import com.sejong.creativesemester.council.entity.Council;
import com.sejong.creativesemester.major.entity.Major;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Entity(name = "USER_TABLE")
public class User extends BaseTimeEntity {

    @Id
    private String studentNum;

    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;

    @ManyToOne
    @JoinColumn(name = "council_id")
    private Council council;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int grade;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}