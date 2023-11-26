package com.sejong.creativesemester.note.controller;

import com.sejong.creativesemester.note.controller.req.SendNoteRequest;
import com.sejong.creativesemester.note.controller.res.FindNoteInBoardWithUserResponse;
import com.sejong.creativesemester.note.controller.res.SendNoteResponse;
import com.sejong.creativesemester.note.service.NoteService;
import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.note.service.req.SendNoteRequestDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class NoteController {
    private final NoteService noteService;

    @Operation(description = "채팅 보내는 api(이거로 쓰세여~!)")
    @PostMapping("/chatRoom/{chatRoomId}/note")
    public SuccessResponse<SendNoteResponse> sendNote(@ApiIgnore Authentication authentication,
                                                      @PathVariable Long chatRoomId, @RequestBody SendNoteRequest request) {
        SendNoteResponse sendNoteResponse = noteService.sendNote(SendNoteRequestDto.builder()
                .senderStudentNum(authentication.getName())
                .chatRoomId(chatRoomId)
                .content(request.getContent()).build());
        return new SuccessResponse(sendNoteResponse);
    }

}
