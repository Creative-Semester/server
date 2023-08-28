package com.sejong.creativesemester.council.entity;

<<<<<<< HEAD
import com.sejong.creativesemester.major.entity.Major;
import lombok.*;
import org.hibernate.annotations.GeneratorType;
=======
import com.sejong.creativesemester.common.domain.BaseTimeEntity;
import com.sejong.creativesemester.major.entity.Major;
import lombok.Getter;
>>>>>>> dev

import javax.persistence.*;

@Getter
<<<<<<< HEAD
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
=======
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
>>>>>>> dev
}
