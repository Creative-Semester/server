package com.sejong.creativesemester.file.entity;

import com.sejong.creativesemester.board.entity.Board;
import com.sejong.creativesemester.common.domain.BaseTimeEntity;
import com.sejong.creativesemester.professor.entity.Professor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "FILE_TABLE")
public class File extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileUrl;
    private String fileName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROFESSOR_ID")
    private Professor professor;

    public void updateBoard(Board board){
        this.board = board;
    }
}
