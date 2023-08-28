package com.sejong.creativesemester.freeboard.entity;

import com.sejong.creativesemester.common.domain.BaseTimeEntity;
import com.sejong.creativesemester.comment.entity.Comment;
import com.sejong.creativesemester.major.entity.Major;
import com.sejong.creativesemester.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity(name = "FREEBOARD_TABLE")
public class FreeBoard extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long freeBoardId;

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
    @JoinColumn(name = "majorid",nullable = false)
    private Major major;

    //댓글을 적은 사용자 아이디
    @OneToMany(mappedBy = "freeBoardId")
    private List<Comment> comment=new ArrayList<>();



    public void getModifiedTitle(String title) {
        this.title = title;
    }

    public void getModifiedContent(String content) {
        this.content = content;
    }

    public void getModifiedImage(String image) {
        this.image = image;
    }

}
