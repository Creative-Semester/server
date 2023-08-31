package com.sejong.creativesemester.vote.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "VOTE_TABLE")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer agreeCnt;
    private Integer opposeCnt;
    private LocalDateTime deadLine;
}
