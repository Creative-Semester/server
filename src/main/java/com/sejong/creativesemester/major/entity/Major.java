package com.sejong.creativesemester.major.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "MAJOR_TABLE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MAJOR_TABLE")
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long majorId;

    @Column(nullable = false)
    private String name;
}
