package com.sejong.creativesemester.professor.entity;

import com.sejong.creativesemester.common.domain.BaseTimeEntity;
import com.sejong.creativesemester.file.entity.File;
import com.sejong.creativesemester.major.entity.Major;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "PROFESSOR_TABLE")
public class Professor extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String location;

    @NotNull
    private String phonenum;

    @NotNull
    private String email;

    @NotNull
    private String majorSub;

    @NotNull
    private String lab;

    @NotNull
    private String intro;

    @JoinColumn(name = "FILE_ID")
    @OneToOne(mappedBy = "professor",orphanRemoval = true)
    private File file;

    @JoinColumn(name = "majorId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Major major;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.REMOVE)
    private List<Course> courses = new ArrayList<>();

}
