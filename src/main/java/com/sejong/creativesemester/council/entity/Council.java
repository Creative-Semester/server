package com.sejong.creativesemester.council.entity;

import com.sejong.creativesemester.common.domain.BaseTimeEntity;
import com.sejong.creativesemester.major.entity.Major;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity(name = "COUNCIL_TABLE")
public class Council extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String introduce;
    private Integer number;
    @OneToOne
    @JoinColumn(name = "major_id")
    private Major major;
}
