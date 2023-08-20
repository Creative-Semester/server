package com.sejong.creativesemester.domain.major.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Major")
public class Major {

    @Id
    @Column(name = "majorId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long majorId;

    @Column(nullable = false)
    private String majorName;
}
