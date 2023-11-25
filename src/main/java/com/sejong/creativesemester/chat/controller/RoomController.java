package com.sejong.creativesemester.chat.controller;

import com.sejong.creativesemester.board.service.BoardService;
import com.sejong.creativesemester.chat.controller.req.PostRequest;
import com.sejong.creativesemester.chat.controller.res.ChatRoomResponse;
import com.sejong.creativesemester.chat.controller.res.RoomResponse;
import com.sejong.creativesemester.chat.domain.ChatRoom;
import com.sejong.creativesemester.chat.service.RoomService;
import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@Validated
@RequestMapping("/chats")
@RequiredArgsConstructor
public class RoomController {

    private final UserService memberService;
    private final RoomService roomService;
    private final BoardService boardService;

    // 채팅방 주소 가져오기
    @PostMapping
    public SuccessResponse getOrCreateRoom(@Valid @RequestBody PostRequest postRequest,
                                           Authentication authentication) {

        String receiveStudentNum = boardService.findBoardById(postRequest.getBoardId()).getUser().getStudentNum();
        // 탈퇴한 회원 확인
        User receiver = memberService.findUser(receiveStudentNum);

        Long roomId = roomService.createRoom(receiver.getStudentNum(), authentication);

        URI location = UriComponentsBuilder.newInstance()
                .path("/chats/{room-id}")
                .buildAndExpand(roomId)
                .toUri();

        return new SuccessResponse(location);

    }

    //  채팅방 열기
    @GetMapping("/{room-id}")
    public SuccessResponse<RoomResponse> getChatRoom(@Positive @PathVariable("room-id") Long roomId,
                                                     Authentication authentication) {
        ChatRoom chatRoom = roomService.findRoom(roomId);
        RoomResponse roomResponse = RoomResponse.builder()
                .senderStudentNum(authentication.getName())
                .receiverStudentNum(chatRoom.getReceiver().getStudentNum())
                .roomId(chatRoom.getId())
                .build();

        return new SuccessResponse(roomResponse);
    }

    // 채팅 목록 조회 -> 로그인한 유저가 참여하고 있는 채팅 목록
    @GetMapping
    public SuccessResponse getChatRooms(Authentication authentication,
                                       @Positive @RequestParam(defaultValue = "1") int page,
                                       @Positive @RequestParam(defaultValue = "10") int size) {

        Page<ChatRoom> roomPage = roomService.findRooms(authentication, page, size);
        PageInfo pageInfo = new PageInfo(page, size, (int)roomPage.getTotalElements(), roomPage.getTotalPages());

        List<ChatRoom> rooms = roomPage.getContent();
        List<RoomResponse> roomResponseList = rooms.stream().map(chatRoom -> RoomResponse.builder()
                .roomId(chatRoom.getId())
                .senderStudentNum(chatRoom.getSender().getStudentNum())
                .receiverStudentNum(chatRoom.getReceiver().getStudentNum())
                .build()).collect(Collectors.toList());

        return new SuccessResponse(
                ChatRoomResponse.builder().roomResponseList(roomResponseList).pageInfo(pageInfo).build()
        );
    }

}