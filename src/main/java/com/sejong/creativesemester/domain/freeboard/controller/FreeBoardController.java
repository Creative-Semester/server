package com.sejong.creativesemester.domain.freeboard.controller;

import com.sejong.creativesemester.domain.freeboard.dto.FreeBoardCreateRequestDto;
import com.sejong.creativesemester.domain.freeboard.service.FreeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FreeBoardController {

    private final FreeBoardService freeBoardService;

    // 게시글 생성
    @PostMapping("/freeBoards/create")
    public ResponseEntity<String> createFreeBoard(Long userId, @RequestBody final FreeBoardCreateRequestDto dto){
        freeBoardService.createFreeBoard(userId, dto);

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
