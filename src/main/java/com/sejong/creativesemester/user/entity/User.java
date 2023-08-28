package com.sejong.creativesemester.user.entity;


import com.sejong.creativesemester.council.entity.Council;
import com.sejong.creativesemester.common.domain.BaseTimeEntity;
import com.sejong.creativesemester.council.entity.Council;
import com.sejong.creativesemester.major.entity.Major;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "USER_TABLE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER_TABLE")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(nullable = false)
    private int isCouncil;

    @Column(nullable = false)
    private String studentNum;
    @ManyToOne
    @JoinColumn(name = "majorId")
    private Major major;
    @ManyToOne
    @JoinColumn(name = "council_id")
    private Council council;
    @Column(nullable = false)
    private int grade;
    @Column(nullable = false)
    private String status;
    @Column(name = "nickname")
    private String nickname;
    @Column(nullable = false)
    private int role;

    @ManyToOne
    @JoinColumn(name = "councilId")
    private Council council;


    @Enumerated(EnumType.STRING)
    private Role role;
}
