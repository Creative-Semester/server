package com.sejong.creativesemester.chat.repository;

import com.sejong.creativesemester.chat.domain.ChatRoom;
import com.sejong.creativesemester.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<ChatRoom,Long> {
    Optional<ChatRoom> findBySenderAndReceiver(User sender, User receiver);
    Page<ChatRoom> findAllBySenderOrReceiver(Pageable pageable, User sender, User receiver);
}
