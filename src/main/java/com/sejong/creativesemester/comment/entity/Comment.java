package com.sejong.creativesemester.comment.entity;

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
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

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

    private LocalDateTime createdTime = LocalDateTime.now();
    private LocalDateTime modifiedTime;

    @Builder
    public Comment(String text){
        this.text = text;
    }
}
