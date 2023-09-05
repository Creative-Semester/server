package com.sejong.creativesemester.board.controller;

import com.sejong.creativesemester.board.entity.BoardType;
import com.sejong.creativesemester.common.format.exception.param.NotMatchConditionException;
import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.board.dto.BoardCreateRequestDto;
import com.sejong.creativesemester.board.dto.BoardDetailResponseDto;
import com.sejong.creativesemester.board.dto.BoardModifyRequestDto;
import com.sejong.creativesemester.board.dto.BoardListResponseDto;
import com.sejong.creativesemester.board.service.BoardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
    public SuccessResponse createBoard(@ApiIgnore Principal principal, @RequestBody final BoardCreateRequestDto dto
            , @Parameter(name = "게시판 종류",required = true,
            schema = @Schema(
            type = "string",
            allowableValues = {"Free"}),
            in = ParameterIn.QUERY) @RequestParam BoardType boardType) throws Exception {
        if(!boardType.getType().equals("Free")){
            throw new NotMatchConditionException();
        }
        boardService.createBoard(principal.getName(), dto,boardType);
        return SuccessResponse.ok();
    }

    //게시판 조회
    @GetMapping("/boards")
    public SuccessResponse getBoards(@ApiIgnore Principal principal,
                                     @RequestParam(required = false, defaultValue = "0", value = "page") int page,
                                     Pageable pageable) {
        if (page==0){
            page = 0;
        }
        else page = page-1;
        BoardListResponseDto boardListResponseDto = boardService.getBoards(principal.getName(),pageable, page);

        return new SuccessResponse(boardListResponseDto);
    }

    //게시판 상세 조회
    @GetMapping("/boards/{boardId}")
    public SuccessResponse getDetailBoards(@ApiIgnore Principal principal, @PathVariable(value = "boardId", required = true) Long boardId){
        BoardDetailResponseDto dto = boardService.getDetailBoards(boardId,principal.getName());
        return new SuccessResponse(dto);
    }

    // 게시글 수정
    @PutMapping("/boards/{boardId}")
    public SuccessResponse modifyBoard(@ApiIgnore Principal principal, @RequestBody BoardModifyRequestDto dto,@PathVariable Long boardId){
        boardService.modifyBoard(principal.getName(), dto,boardId);
        return new SuccessResponse("modify success");
    }

    // 게시글 삭제
    @DeleteMapping("/boards/{boardId}")
    public SuccessResponse deleteBoard(@ApiIgnore Principal principal, @PathVariable(value = "boardId", required = true) Long boardId){
        boardService.deleteBoard(principal.getName(), boardId);

        return new SuccessResponse("delete Success");
    }

}