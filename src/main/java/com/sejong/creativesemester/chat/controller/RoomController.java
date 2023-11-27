package com.sejong.creativesemester.chat.controller;

import com.sejong.creativesemester.board.service.BoardService;
import com.sejong.creativesemester.chat.controller.res.ChatRoomInfoResponse;
import com.sejong.creativesemester.chat.controller.res.CreatedChatRoomInfoResponse;
import com.sejong.creativesemester.chat.controller.res.ChatRoomListResponse;
import com.sejong.creativesemester.chat.controller.res.ChatRoomDetailInfoResponse;
import com.sejong.creativesemester.chat.domain.ChatRoom;
import com.sejong.creativesemester.chat.service.RoomService;
import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.note.entity.Note;
import com.sejong.creativesemester.note.entity.dto.NoteInfo;
import com.sejong.creativesemester.note.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@Validated
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final BoardService boardService;
    private final NoteService noteService;

    // 채팅방 주소 가져오기
    @Operation(description = "채팅방을 생성 하는 api(채팅방이 존재한다면 조회)")
    @PostMapping("/boardId/{boardId}/chats")
    public SuccessResponse<CreatedChatRoomInfoResponse> getOrCreateRoom(@PathVariable Long boardId,
                                                                        @ApiIgnore Authentication authentication) {

        String receiveStudentNum = boardService.findBoardById(boardId).getUser().getStudentNum();

        Long roomId = roomService.createRoom(boardId, receiveStudentNum, authentication);

        return new SuccessResponse(CreatedChatRoomInfoResponse.builder().roomId(roomId).build());

    }

    //  채팅방 열기
    @Operation(description = "해당 채팅방의 상세사항")
    @GetMapping("/chatRoom/{roomId}")
    public SuccessResponse<ChatRoomDetailInfoResponse> getChatRoom(@Positive @PathVariable("roomId") Long roomId,
                                                                   @ApiIgnore Authentication authentication) {
        ChatRoom chatRoom = roomService.findRoom(roomId);
        List<Note> notesInChatRoom = noteService.findNotesInChatRoom(chatRoom);
        ChatRoomDetailInfoResponse chatRoomDetailInfoResponse = ChatRoomDetailInfoResponse.builder()
                .noteInfos(notesInChatRoom.stream().map(note -> NoteInfo.builder()
                        .contents(note.getContents())
                        .sendTime(note.getCreatedTime())
                        .senderStudentNum(note.getSender().getStudentNum())
                        .build()).collect(Collectors.toList()))
                .roomId(chatRoom.getId())
                .receiverStudentNum(chatRoom.getReceiver().getStudentNum())
                .senderStudentNum(chatRoom.getSender().getStudentNum())
                .build();

        return new SuccessResponse(chatRoomDetailInfoResponse);
    }

    // 채팅 목록 조회 -> 로그인한 유저가 참여하고 있는 채팅 목록
    @Operation(description = "사용자가 참여중인 채팅목록 조회")
    @GetMapping("/chatRoom")
    public SuccessResponse<ChatRoomListResponse> getChatRooms(@ApiIgnore Authentication authentication,
                                                              @RequestParam(defaultValue = "0") int page
    ) {

        Page<ChatRoom> roomPage = roomService.findRooms(authentication, page);
        List<ChatRoom> rooms = roomPage.getContent();
        List<ChatRoomInfoResponse> chatRoomDetailInfoResponseList = rooms.stream().map(chatRoom -> ChatRoomInfoResponse.builder()
                .roomId(chatRoom.getId())
                .senderStudentNum(chatRoom.getSender().getStudentNum())
                .receiverStudentNum(chatRoom.getReceiver().getStudentNum())
                .noteInfos(getBuild(chatRoom))
                .boardName(chatRoom.getBoard().getTitle())
                .build()).collect(Collectors.toList());

        return new SuccessResponse(
                ChatRoomListResponse.builder()
                        .chatRoomDetailInfoResponseList(chatRoomDetailInfoResponseList)
                        .currentPage(roomPage.getNumber())
                        .totalPages(roomPage.getTotalPages()).build()
        );
    }

    private NoteInfo getBuild(ChatRoom chatRoom) {
        Note lastNoteAtChatRoom = getLastNoteAtChatRoom(chatRoom);
        if (lastNoteAtChatRoom == null) {
            return null;
        }
        return NoteInfo.builder()
                .senderStudentNum(getLastNoteAtChatRoom(chatRoom).getSender().getStudentNum())
                .sendTime(getLastNoteAtChatRoom(chatRoom).getCreatedTime())
                .contents(getLastNoteAtChatRoom(chatRoom).getContents())
                .build();
    }

    private Note getLastNoteAtChatRoom(ChatRoom chatRoom) {
        return noteService.findLastNoteAtChatRoom(chatRoom);
    }

}