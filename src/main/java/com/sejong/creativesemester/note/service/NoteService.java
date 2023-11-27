package com.sejong.creativesemester.note.service;

import com.sejong.creativesemester.board.entity.Board;
import com.sejong.creativesemester.board.service.BoardService;
import com.sejong.creativesemester.chat.domain.ChatRoom;
import com.sejong.creativesemester.chat.service.RoomService;
import com.sejong.creativesemester.common.format.exception.chatroom.NotFoundChatRoomException;
import com.sejong.creativesemester.note.controller.res.SendNoteResponse;
import com.sejong.creativesemester.note.entity.Note;
import com.sejong.creativesemester.note.repository.NoteRepository;
import com.sejong.creativesemester.note.service.req.SendNoteRequestDto;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserService userService;
    private final RoomService roomService;
    public SendNoteResponse sendNote(SendNoteRequestDto sendNoteRequestDto) {
        ChatRoom room = roomService.findRoom(sendNoteRequestDto.getChatRoomId());
        User sender = userService.findUser(sendNoteRequestDto.getSenderStudentNum());
        User receiver = room.getReceiver();
        Note note = Note.builder()
                .chatRoom(room)
                .contents(sendNoteRequestDto.getContent())
                .receiver(receiver)
                .sender(sender)
                .build();

        Note savedNote = noteRepository.save(note);
        return SendNoteResponse.builder()
                .content(savedNote.getContents())
                .receiverStudentNum(savedNote.getReceiver().getStudentNum())
                .senderStudentNum(savedNote.getSender().getStudentNum())
                .build();
    }

    public List<Note> findNotesInChatRoom(ChatRoom chatRoom) {
        return noteRepository.findByChatRoom(chatRoom);
    }

    public Note findLastNoteAtChatRoom(ChatRoom chatRoom){
        return noteRepository.findByChatRoomOrderByCreatedTimeDesc(chatRoom).stream().findFirst().orElseThrow(NotFoundChatRoomException::new);
    }
}
