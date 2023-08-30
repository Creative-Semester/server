package com.sejong.creativesemester.promise.entity;

import com.sejong.creativesemester.common.domain.BaseTimeEntity;
import com.sejong.creativesemester.department.entity.Department;

import javax.persistence.*;
@Entity(name = "PROMISE_TABLE")
public class Promise extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private boolean implementation;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}