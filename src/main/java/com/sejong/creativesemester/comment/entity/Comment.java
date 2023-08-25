package com.sejong.creativesemester.comment.entity;

import com.sejong.creativesemester.config.BaseTimeEntity;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.freeboard.entity.FreeBoard;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "COMMENT_TABLE")
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "freeBoardId")
    private FreeBoard freeBoard;

    // 학과 게시판 외래키 추가


    @Builder
    public Comment(String text){
        this.text = text;
    }
}
