package com.sejong.creativesemester.freeboard.controller;

import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.common.format.success.SuccessResponseStatus;
import com.sejong.creativesemester.freeboard.dto.FreeBoardCreateRequestDto;
import com.sejong.creativesemester.freeboard.dto.FreeBoardDetailResponseDto;
import com.sejong.creativesemester.freeboard.dto.FreeBoardModifyRequestDto;
import com.sejong.creativesemester.freeboard.dto.FreeBoardResponseDto;
import com.sejong.creativesemester.freeboard.service.FreeBoardService;
import com.sun.net.httpserver.Authenticator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.security.Principal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FreeBoardController {

    private final FreeBoardService freeBoardService;

    // 게시글 생성
    @PostMapping("/freeBoards/create")
    public SuccessResponse createFreeBoard(Principal principal, @RequestBody final FreeBoardCreateRequestDto dto){
        freeBoardService.createFreeBoard(principal.getName(), dto);


        return new SuccessResponse("success");
    }

    //게시판 조회
    @GetMapping("/freeBoards/{majorId}")
    public SuccessResponse getFreeBoards(@PathVariable Long majorId,
                                         @PageableDefault(size = 20, sort = "createdTime", direction = Sort.Direction.DESC) Pageable pageable){

        freeBoardService.getFreeBoards(pageable, majorId);

        return new SuccessResponse("success");
    }

    //게시판 상세 조회
    @GetMapping("/freeBoards/{freeBoardId}")
    public SuccessResponse getDetailFreeBoards(@PathVariable(value = "freeBoardId", required = true) Long freeBoardId){
        FreeBoardDetailResponseDto dto = freeBoardService.getDetailFreeBoards(freeBoardId);

        return new SuccessResponse(dto);
    }

    // 게시글 수정
    @PatchMapping("/freeBoards/modify")
    public SuccessResponse modifyFreeBoard(String studentNum, @RequestBody FreeBoardModifyRequestDto dto){
        freeBoardService.modifyFreeBoard(studentNum, dto);

        return new SuccessResponse("modify success");
    }

    // 게시글 삭제
    @DeleteMapping("/freeBoards/{freeBoardId}")
    public SuccessResponse deleteFreeBoard(String studentNum, @PathVariable(value = "freeBoardId", required = true) Long freeBoardId){
        freeBoardService.deleteFreeBoard(studentNum, freeBoardId);

        return new SuccessResponse("delete Success");
    }

}