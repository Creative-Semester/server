package com.sejong.creativesemester.user.entity;


<<<<<<< HEAD
import com.sejong.creativesemester.config.BaseTimeEntity;
import com.sejong.creativesemester.council.entity.Council;
=======
import com.sejong.creativesemester.common.domain.BaseTimeEntity;
>>>>>>> dev
import com.sejong.creativesemester.major.entity.Major;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "USER_TABLE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
<<<<<<< HEAD
@Table(name = "USER_TABLE")
=======
>>>>>>> dev
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
=======
    private Long id;
    @Column(nullable = false)
    private int isCouncil;

    @Column(nullable = false)
>>>>>>> dev
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
<<<<<<< HEAD

    @ManyToOne
    @JoinColumn(name = "key")
    private Council council;


=======
>>>>>>> dev
}
