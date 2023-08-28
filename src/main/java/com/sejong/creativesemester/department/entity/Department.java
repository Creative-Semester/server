package com.sejong.creativesemester.department.entity;

import com.sejong.creativesemester.common.domain.BaseTimeEntity;
import com.sejong.creativesemester.major.entity.Major;
import com.sejong.creativesemester.promise.entity.Promise;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "DEPARTMENT_TABLE")
public class Department extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;

    @OneToMany(mappedBy = "department")
    private List<Promise> promises = new ArrayList<>();
}