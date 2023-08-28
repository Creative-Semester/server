package com.sejong.creativesemester.council.entity;

import com.sejong.creativesemester.major.entity.Major;
import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "COUNCIL_TABLE")
public class Council {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long councilId;

    @Column(nullable = false)
    private String name;

    private String introduce;

    @OneToOne
    @JoinColumn(name = "majorId")
    private Major major;

    @Column(nullable = false)
    private int number;
}
