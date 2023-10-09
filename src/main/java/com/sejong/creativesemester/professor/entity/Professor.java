package com.sejong.creativesemester.professor.entity;

import com.sejong.creativesemester.common.domain.BaseTimeEntity;
import com.sejong.creativesemester.major.entity.Major;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PROFESSOR_TABLE")
public class Professor extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String location;

    @NotNull
    private String phonenum;

    @NotNull
    private String email;

    @NotNull
    private String majorSub;

    @NotNull
    private String lab;

    @NotNull
    private String intro;

    @NotNull
    private String image;

    @JoinColumn(name = "majorId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Major major;

}
