package com.sejong.creativesemester.file.entity;

import com.sejong.creativesemester.board.entity.Board;
import com.sejong.creativesemester.common.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class File extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String fileUrl;
    @Column(columnDefinition = "TEXT")
    private String fileName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    public void updateBoard(Board board){
        this.board = board;
    }
}
