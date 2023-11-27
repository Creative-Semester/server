package com.sejong.creativesemester.note.entity;

import com.sejong.creativesemester.board.entity.Board;
import com.sejong.creativesemester.chat.domain.ChatRoom;
import com.sejong.creativesemester.common.domain.BaseTimeEntity;
import com.sejong.creativesemester.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Note extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

}
