package com.sejong.creativesemester.board.entity;

import com.sejong.creativesemester.common.domain.BaseTimeEntity;
import com.sejong.creativesemester.comment.entity.Comment;
import com.sejong.creativesemester.major.entity.Major;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.vote.entity.Vote;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity(name = "FREEBOARD_TABLE")
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;
    private String image;

    //학과 아이디 추가
    @ManyToOne
    @JoinColumn(name = "majorId",nullable = false)
    private Major major;

    //댓글을 적은 사용자 아이디
    @OneToMany(mappedBy = "board")
    private List<Comment> comment = new ArrayList<>();

    @JoinColumn(name = "voteId")
    @OneToOne
    private Vote vote;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    public void update(String title, String content, String image){
        this.title = title;
        this.content = content;
        this.image = image;
    }


}