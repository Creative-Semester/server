package com.sejong.creativesemester.department.domain;

import com.sejong.creativesemester.common.domain.BaseTimeEntity;
import com.sejong.creativesemester.major.entity.Major;

import javax.persistence.*;

@Entity(name = "DEPARTMENT_TABLE")
public class Department extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;
}
