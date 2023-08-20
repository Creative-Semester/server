package com.sejong.creativesemester.domain.user.entity;


import com.sejong.creativesemester.domain.major.entity.Major;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;

    @Column(nullable = false)
    private int isCouncil;

    @Column(nullable = false)
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

    private LocalDateTime createdTime = LocalDateTime.now();
    private LocalDateTime modifiedTime;

}
