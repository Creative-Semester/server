package com.sejong.creativesemester.council.entity;

import com.sejong.creativesemester.common.domain.BaseTimeEntity;
import com.sejong.creativesemester.major.entity.Major;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity(name = "GRANTCODE_TABLE")
public class GrantCode{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "GrantCode", nullable = false)
    private String grantCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

}
