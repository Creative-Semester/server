package com.sejong.creativesemester.user.entity;


import com.sejong.creativesemester.common.domain.BaseTimeEntity;
import com.sejong.creativesemester.major.entity.Major;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "USER_TABLE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int isCouncil;

    @Column(nullable = false)
    private String studentNum;

    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;

    @Column(nullable = false)
    private int grade;

    @Column(nullable = false)
    private int status;

    @Column(name = "nickname")
    private String nickname;

    @Column(nullable = false)
    private int role;
}
