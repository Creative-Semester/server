package com.sejong.creativesemester.user.entity;


import com.sejong.creativesemester.board.entity.Board;
import com.sejong.creativesemester.comment.entity.Comment;
import com.sejong.creativesemester.common.domain.BaseTimeEntity;
import com.sejong.creativesemester.council.entity.Council;
import com.sejong.creativesemester.major.entity.Major;
import com.sejong.creativesemester.professor.entity.Evaluation;
import com.sejong.creativesemester.vote.entity.Vote;
import com.sejong.creativesemester.voter.entity.Voter;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity(name = "USER_TABLE")
public class User extends BaseTimeEntity {

    @Id
    private String studentNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "council_id")
    private Council council;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int grade;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<Voter> voter = new ArrayList<>();


    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void updateRole(){
        this.role = Role.ROLE_COUNCIL;
    }

}