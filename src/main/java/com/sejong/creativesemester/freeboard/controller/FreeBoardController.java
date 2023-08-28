package com.sejong.creativesemester.freeboard.controller;

import com.sejong.creativesemester.freeboard.dto.FreeBoardCreateRequestDto;
import com.sejong.creativesemester.freeboard.dto.FreeBoardDetailResponseDto;
import com.sejong.creativesemester.freeboard.dto.FreeBoardModifyRequestDto;
import com.sejong.creativesemester.freeboard.dto.FreeBoardResponseDto;
import com.sejong.creativesemester.freeboard.service.FreeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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

        return ResponseEntity.ok().body("creation success");
    }

    //게시판 조회
    @GetMapping("/freeBoards/{majorId}")
    public ResponseEntity<FreeBoardResponseDto> getFreeBoards(@PathVariable Long majorId,
                                                              @RequestParam(required = false, defaultValue = "0", value = "page") int page,
                                                              Pageable pageable){
        if (page==0){
            page = 0;
        }
        else page = page-1;

        FreeBoardResponseDto freeBoardResponseDto = freeBoardService.getFreeBoards(pageable, page, majorId);

        return ResponseEntity.ok(freeBoardResponseDto);
    }

    //게시판 상세 조회
    @GetMapping("/freeBoards/{freeBoardId}")
    public ResponseEntity<FreeBoardDetailResponseDto> getDetailFreeBoards(@PathVariable(value = "freeBoardId", required = true) Long freeBoardId){
        FreeBoardDetailResponseDto dto = freeBoardService.getDetailFreeBoards(freeBoardId);

        return ResponseEntity.ok().body(dto);
    }

    // 게시글 수정
    @PatchMapping("/freeBoards/modify")
    public ResponseEntity<String> modifyFreeBoard(String studentNum, @RequestBody FreeBoardModifyRequestDto dto){
        freeBoardService.modifyFreeBoard(studentNum, dto);

        return ResponseEntity.ok().body("modification success");
    }

    // 게시글 삭제
    @DeleteMapping("/freeBoards/{freeBoardId}")
    public ResponseEntity<String> deleteFreeBoard(String studentNum, @PathVariable(value = "freeBoardId", required = true) Long freeBoardId){
        freeBoardService.deleteFreeBoard(studentNum, freeBoardId);

        return ResponseEntity.ok().body("delete success");
    }

}
