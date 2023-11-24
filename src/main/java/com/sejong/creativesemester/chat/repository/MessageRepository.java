package com.sejong.creativesemester.chat.repository;

import com.sejong.creativesemester.chat.domain.ChatMessage;
import com.sejong.creativesemester.chat.domain.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<ChatMessage,Long> {
    Page<ChatMessage> findByChatRoom(Pageable pageable, ChatRoom chatRoom);
}
