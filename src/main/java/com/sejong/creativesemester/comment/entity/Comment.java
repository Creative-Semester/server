package com.sejong.creativesemester.comment.entity;

import com.sejong.creativesemester.board.entity.Board;
import com.sejong.creativesemester.common.domain.BaseTimeEntity;
import com.sejong.creativesemester.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "COMMENT_TABLE")
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    private Integer reportCnt;

    public void reportComment(){
        this.reportCnt++;
    }

    @Builder
    public Comment(String text, Board board,User user) {
        this.text = text;
        this.board = board;
        this.user = user;
        this.reportCnt = 0;
    }
}