package com.sejong.creativesemester.chat.controller;

import com.sejong.creativesemester.board.service.BoardService;
import com.sejong.creativesemester.chat.controller.req.PostRequest;
import com.sejong.creativesemester.chat.controller.res.ChatRoomInfoResponse;
import com.sejong.creativesemester.chat.controller.res.ChatRoomListResponse;
import com.sejong.creativesemester.chat.controller.res.ChatRoomDetailInfoResponse;
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
import springfox.documentation.annotations.ApiIgnore;

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
    public SuccessResponse<ChatRoomInfoResponse> getOrCreateRoom(@Valid @RequestBody PostRequest postRequest,
                                                                 @ApiIgnore Authentication authentication) {

        String receiveStudentNum = boardService.findBoardById(postRequest.getBoardId()).getUser().getStudentNum();
        // 탈퇴한 회원 확인
        User receiver = memberService.findUser(receiveStudentNum);

        Long roomId = roomService.createRoom(receiver.getStudentNum(), authentication);

        return new SuccessResponse(ChatRoomInfoResponse.builder().roomId(roomId).build());

    }

    //  채팅방 열기
    @GetMapping("/{room-id}")
    public SuccessResponse<ChatRoomDetailInfoResponse> getChatRoom(@Positive @PathVariable("room-id") Long roomId,
                                                                   @ApiIgnore Authentication authentication) {
        ChatRoom chatRoom = roomService.findRoom(roomId);
        ChatRoomDetailInfoResponse chatRoomDetailInfoResponse = ChatRoomDetailInfoResponse.builder()
                .senderStudentNum(authentication.getName())
                .receiverStudentNum(chatRoom.getReceiver().getStudentNum())
                .roomId(chatRoom.getId())
                .build();

        return new SuccessResponse(chatRoomDetailInfoResponse);
    }

    // 채팅 목록 조회 -> 로그인한 유저가 참여하고 있는 채팅 목록
    @GetMapping
    public SuccessResponse<ChatRoomListResponse> getChatRooms(@ApiIgnore Authentication authentication,
                                                              @Positive @RequestParam(defaultValue = "1") int page,
                                                              @Positive @RequestParam(defaultValue = "10") int size) {

        Page<ChatRoom> roomPage = roomService.findRooms(authentication, page, size);
        PageInfo pageInfo = new PageInfo(page, size, (int) roomPage.getTotalElements(), roomPage.getTotalPages());

        List<ChatRoom> rooms = roomPage.getContent();
        List<ChatRoomDetailInfoResponse> chatRoomDetailInfoResponseList = rooms.stream().map(chatRoom -> ChatRoomDetailInfoResponse.builder()
                .roomId(chatRoom.getId())
                .senderStudentNum(chatRoom.getSender().getStudentNum())
                .receiverStudentNum(chatRoom.getReceiver().getStudentNum())
                .build()).collect(Collectors.toList());

        return new SuccessResponse(
                ChatRoomListResponse.builder().chatRoomDetailInfoResponseList(chatRoomDetailInfoResponseList).pageInfo(pageInfo).build()
        );
    }

}