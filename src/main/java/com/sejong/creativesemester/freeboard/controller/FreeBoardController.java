package com.sejong.creativesemester.freeboard.controller;

import com.sejong.creativesemester.freeboard.dto.FreeBoardCreateRequestDto;
import com.sejong.creativesemester.freeboard.service.FreeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FreeBoardController {

    private final FreeBoardService freeBoardService;

    // 게시글 생성
    @PostMapping("/freeBoards/create")
    public ResponseEntity<String> createFreeBoard(Principal principal, @RequestBody final FreeBoardCreateRequestDto dto){
        freeBoardService.createFreeBoard(principal.getName(), dto);

        return ResponseEntity.ok("success");
    }

    // 게시판 조회
//    @GetMapping("/freeBoards")
//
//    // 게시글 수정
//    @PutMapping("/freeBoards/{freeBoardId}")
//
//    // 게시글 삭제
//    @DeleteMapping("/freeBoards/{freeBoardId}")

}
