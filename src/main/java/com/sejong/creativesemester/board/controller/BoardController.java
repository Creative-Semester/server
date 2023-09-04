package com.sejong.creativesemester.board.controller;

import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.board.dto.BoardCreateRequestDto;
import com.sejong.creativesemester.board.dto.BoardDetailResponseDto;
import com.sejong.creativesemester.board.dto.BoardModifyRequestDto;
import com.sejong.creativesemester.board.dto.BoardResponseDto;
import com.sejong.creativesemester.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 생성
    @PostMapping("/boards/create")
    public  SuccessResponse createBoard(Principal principal, @RequestBody final BoardCreateRequestDto dto){
        boardService.createBoard(principal.getName(), dto);


        return new SuccessResponse("success");
    }

    //게시판 조회
    @GetMapping("/boards/{majorId}")
    public SuccessResponse getBoards(@PathVariable(value = "majorId", required = true) Long majorId,
                                     @PageableDefault(size = 20, sort = "createdTime", direction = Sort.Direction.DESC) Pageable pageable){

        BoardResponseDto dto = boardService.getBoards(pageable, 0, majorId);

        return new SuccessResponse(dto);
    }

    //게시판 상세 조회
    @GetMapping("/boards/{majorId}/{boardId}")
    public SuccessResponse getDetailBoards(@PathVariable(value = "majorId", required = true) Long majorId, @PathVariable(value = "boardId", required = true) Long boardId){
        BoardDetailResponseDto dto = boardService.getDetailBoards(majorId, boardId);

        return new SuccessResponse(dto);
    }

    // 게시글 수정
    @PatchMapping("/boards/modify")
    public SuccessResponse modifyBoard(String studentNum, @RequestBody BoardModifyRequestDto dto){
        boardService.modifyBoard(studentNum, dto);

        return new SuccessResponse("modify success");
    }

    // 게시글 삭제
    @DeleteMapping("/boards/{boardId}")
    public SuccessResponse deleteBoard(String studentNum, @PathVariable(value = "boardId", required = true) Long boardId){
        boardService.deleteBoard(studentNum, boardId);

        return new SuccessResponse("delete Success");
    }

}