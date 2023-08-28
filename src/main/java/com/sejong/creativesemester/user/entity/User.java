package com.sejong.creativesemester.user.entity;


import com.sejong.creativesemester.config.BaseTimeEntity;
import com.sejong.creativesemester.council.entity.Council;
import com.sejong.creativesemester.major.entity.Major;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER_TABLE")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String studentNum;

    @ManyToOne
    @JoinColumn(name = "majorId")
    private Major major;

    @Column(nullable = false)
    private int grade;

    @Column(nullable = false)
    private int status;

    @Column(name = "nickname")
    private String nickname;

    @Column(nullable = false)
    private int role;

    @ManyToOne
    @JoinColumn(name = "key")
    private Council council;


}
