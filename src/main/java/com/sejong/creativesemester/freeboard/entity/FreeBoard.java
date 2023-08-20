package com.sejong.creativesemester.freeboard.entity;

import com.sejong.creativesemester.comment.entity.Comment;
import com.sejong.creativesemester.major.entity.Major;
import com.sejong.creativesemester.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "FreeBoard")
public class FreeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long freeBoardId;

    @ManyToOne
    @JoinColumn(name = "userId")
    @Column(name = "userId")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;


    private String image;
    private LocalDateTime createdTime = LocalDateTime.now();
    private LocalDateTime modifiedTime;

    //학과 아이디 추가
    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "majorId")
    private Major major;

    //댓글을 적은 사용자 아이디
    @OneToMany(mappedBy = "freeBoard")
    private Collection<Comment> comment;



    public void update(String title, String content, String image){
        this.title = title;
        this.content = content;
        this.image = image;
        this.modifiedTime = LocalDateTime.now();
    }

}
