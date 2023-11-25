package com.sejong.creativesemester.chat.service;

import com.sejong.creativesemester.chat.domain.ChatRoom;
import com.sejong.creativesemester.chat.repository.RoomRepository;
import com.sejong.creativesemester.login.domain.AuthUser;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Slf4j
@Service
@RequiredArgsConstructor
public class RoomService {
    private final UserService memberService;
    private final RoomRepository roomRepository;

    public Long createRoom(String receiverStudentNum, Authentication authentication) {
        User receiver = memberService.findUser(receiverStudentNum);
        User sender = memberService.findUser(authentication.getName());

        // 둘의 채팅이 있는 지 확인
        Optional<ChatRoom> optionalChatRoom = roomRepository.findBySenderAndReceiver(sender, receiver);
        Optional<ChatRoom> optionalChatRoom2 = roomRepository.findBySenderAndReceiver(receiver, sender);

        ChatRoom chatRoom = null;

        if(optionalChatRoom.isPresent()) {
            chatRoom = optionalChatRoom.get();
            log.info("find chat room");
            return chatRoom.getId();
        } else if (optionalChatRoom2.isPresent()) {
            chatRoom = optionalChatRoom2.get();
            log.info("find chat room");
            return chatRoom.getId();
        } else {
            chatRoom = ChatRoom.builder().sender(sender).receiver(receiver).build();
            log.info("create chat room");
        }

        ChatRoom saveChatRoom = roomRepository.save(chatRoom);

        return saveChatRoom.getId();
    }

    // 유저의 채팅 목록 가져오기
    public Page<ChatRoom> findRooms(Authentication authentication, int page, int size) {
        User sender = memberService.findUser(authentication.getName());
        Pageable pageable = PageRequest.of(page-1 , size, Sort.by("roomId").descending());
        Page<ChatRoom> chatRooms = roomRepository.findAllBySenderOrReceiver(pageable, sender, sender);

        return chatRooms;
    }

    // 채팅방 하나 찾기
    public ChatRoom findRoom(Long roomId) {
        ChatRoom chatRoom = findExistRoom(roomId);

        return chatRoom;
    }

    // 채팅방 존재 검증
    private ChatRoom findExistRoom(Long roomId) {
        Optional<ChatRoom> optionalChatRoom = roomRepository.findById(roomId);

        ChatRoom findChatRoom = optionalChatRoom.orElseThrow(
                ()->new IllegalArgumentException("없는 채팅방")
        );


        return findChatRoom;
    }

}
