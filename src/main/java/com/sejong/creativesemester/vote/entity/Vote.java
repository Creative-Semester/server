package com.sejong.creativesemester.vote.entity;

import com.sejong.creativesemester.board.entity.Board;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.voter.entity.Voter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "VOTE_TABLE")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer agreeCnt;
    private Integer opposeCnt;
    private LocalDateTime deadLine;

    @OneToOne(mappedBy = "vote",fetch = FetchType.LAZY)
    private Board board;

    @OneToMany(mappedBy = "vote",orphanRemoval = true)
    @Builder.Default
    private List<Voter> voter=new ArrayList<>();

    public static Vote initVote(LocalDateTime deadLine){
        return Vote.builder()
                .deadLine(deadLine)
                .agreeCnt(0)
                .opposeCnt(0)
                .build();
    }

    public void increaseAgreeCnt(User user) {
        this.agreeCnt++;
    }
    public void increaseOpposeCnt(User user){
        this.opposeCnt++;
    }
    public void makeVoteToBoard(){

    }
}
