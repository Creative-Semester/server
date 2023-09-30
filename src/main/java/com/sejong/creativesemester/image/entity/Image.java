package com.sejong.creativesemester.image.entity;

import com.sejong.creativesemester.board.entity.Board;
import com.sejong.creativesemester.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Image extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;
    private String imageName;
    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    public void updateBoard(Board board){
        this.board = board;
    }
}
