package com.sejong.creativesemester.board.controller;

import com.sejong.creativesemester.board.entity.BoardType;
import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.board.dto.BoardCreateRequestDto;
import com.sejong.creativesemester.board.dto.BoardDetailResponseDto;
import com.sejong.creativesemester.board.dto.BoardModifyRequestDto;
import com.sejong.creativesemester.board.dto.BoardResponseDto;
import com.sejong.creativesemester.board.service.BoardService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Example;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 생성
    @ApiOperation(
        value = "게시글 생성",
            notes = "자유 게시판의 게시글 생성 api"
    )
    @PostMapping("/boards")
    public SuccessResponse createBoard(@RequestParam String studentNum, @RequestBody final BoardCreateRequestDto dto
            ,@Parameter(name = "게시판 종류",required = true,
            schema = @Schema(
            type = "string",
            allowableValues = {"Free"}),
            in = ParameterIn.QUERY) @RequestParam BoardType boardType){
        boardService.createBoard(studentNum, dto,boardType);
        return SuccessResponse.ok();
    }

    //게시판 조회
    @GetMapping("/boards/{majorId}")
    public SuccessResponse getBoards(@PathVariable Long majorId,
                                     @RequestParam(required = false, defaultValue = "0", value = "page") int page,
                                     Pageable pageable) {
        if (page==0){
            page = 0;
        }
        else page = page-1;

        BoardResponseDto boardResponseDto = boardService.getBoards(pageable, page, majorId);

        return new SuccessResponse(boardResponseDto);
    }

    //게시판 상세 조회
    @GetMapping("/boards/{boardId}")
    public SuccessResponse getDetailBoards(@PathVariable(value = "boardId", required = true) Long boardId){
        BoardDetailResponseDto dto = boardService.getDetailBoards(boardId);

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