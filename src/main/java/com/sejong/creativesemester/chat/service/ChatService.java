package com.sejong.creativesemester.chat.service;

import com.sejong.creativesemester.chat.domain.ChatMessage;
import com.sejong.creativesemester.chat.domain.ChatRoom;
import com.sejong.creativesemester.chat.repository.MessageRepository;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {
    private final UserService memberService;
    private final RoomService roomService;
    private final MessageRepository messageRepository;
    public void saveMessage(MessageDto dto, Long roomId) {
        User member = memberService.findUser(dto.getSenderStudentNum());
        ChatRoom chatRoom = roomService.findRoom(roomId);

        ChatMessage chatMessage = ChatMessage
                .builder()
                .content(dto.getContent())
                .sender(member)
                .chatRoom(chatRoom)
                .sendTime(now())
                .build();

        messageRepository.save(chatMessage);
        log.info("메세지 저장 완료");
    }

    public Page<ChatMessage> findMessages(long roomId, int page, int size) {
        ChatRoom chatRoom = roomService.findRoom(roomId);

        Pageable pageable = PageRequest.of(page-1, size, Sort.by("messageId").descending());
        Page<ChatMessage> messages = messageRepository.findByChatRoom(pageable, chatRoom);

        return messages;
    }
}
