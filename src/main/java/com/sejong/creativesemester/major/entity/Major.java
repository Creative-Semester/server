package com.sejong.creativesemester.major.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Entity(name = "MAJOR_TABLE")
@Getter
@NoArgsConstructor()
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}