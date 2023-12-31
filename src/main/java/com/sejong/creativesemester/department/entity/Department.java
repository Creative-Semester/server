package com.sejong.creativesemester.department.entity;

import com.sejong.creativesemester.common.domain.BaseTimeEntity;
import com.sejong.creativesemester.council.entity.Council;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "council_id")
    private Council council;

    @OneToMany(mappedBy = "department")
    private List<Promise> promises = new ArrayList<>();
}