package com.sejong.creativesemester.note.repository;

import com.sejong.creativesemester.chat.domain.ChatRoom;
import com.sejong.creativesemester.note.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {

    List<Note> findByChatRoom(ChatRoom chatRoom);

    List<Note> findByChatRoomOrderByCreatedTimeDesc(ChatRoom chatRoom);
}
