package com.sejong.creativesemester.vote.entity;

import com.sejong.creativesemester.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @OneToOne(mappedBy = "vote")
    private Board board;

    public static Vote initVote(Board board){
        return Vote.builder()
                .board(board)
                .deadLine(now())
                .agreeCnt(0)
                .opposeCnt(0)
                .build();
    }

}
