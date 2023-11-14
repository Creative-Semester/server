package com.sejong.creativesemester.professor.entity;

import com.sejong.creativesemester.comment.entity.Comment;
import com.sejong.creativesemester.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "COURSE_TABLE")
public class Course extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professorId")
    private Professor professor;

    @NotNull
    private String title;

    @NotNull
    private String classification;

    @NotNull
    private String grade;

    @NotNull
    private String score;

    @OneToMany(mappedBy = "course", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Evaluation> evaluations = new ArrayList<>();
}
