package com.sejong.creativesemester.board.controller;

import com.sejong.creativesemester.board.dto.*;
import com.sejong.creativesemester.board.entity.BoardType;
import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.board.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 게시글 생성
    @ApiOperation(
            value = "게시글 생성", notes = "학생회게시글과 자유게시글을 작성하는 api입니다."
    )
    @PostMapping()
    public SuccessResponse createBoard(@ApiIgnore Authentication authentication
            , @Valid @RequestBody final BoardCreateRequestDto dto
            , @Parameter(name = "게시판 종류", required = true,
            schema = @Schema(
                    type = "string"),
            in = ParameterIn.QUERY) @RequestParam BoardType boardType
            , @Parameter(name = "투표 여부", required = true, schema = @Schema(
            type = "boolean"), in = ParameterIn.QUERY) @RequestParam Boolean isVote) throws Exception {
        boardService.createBoard(authentication, dto, boardType, isVote);
        return SuccessResponse.ok();
    }

    //게시판 조회
    @ApiOperation(
            value = "게시글 목록 조회",
            notes = "게시판 들어왔을때 게시글 목록을 조회해주는 api"
    )
    @GetMapping()
    public SuccessResponse<BoardListResponseDto> getBoards(@ApiIgnore Authentication authentication
            , @RequestParam(required = false, defaultValue = "0", value = "page") int page
            , @RequestParam BoardType boardType
    ) {
        BoardListResponseDto boardListResponseDto = boardService.getBoards(authentication.getName(), page,boardType);
        return new SuccessResponse(boardListResponseDto);
    }

    //게시판 상세 조회
    @ApiOperation(
            value = "게시글 상세 조회",
            notes = "게시글 목록에서 특정 글을 눌렀을때 해당 글의 상세 내용 조회 api"
    )
    @GetMapping("/{boardId}")
    public SuccessResponse<BoardDetailResponseDto> getDetailBoards(@ApiIgnore Principal principal
            , @PathVariable(value = "boardId", required = true) Long boardId) {
        BoardDetailResponseDto dto = boardService.getDetailBoards(boardId, principal.getName());

        return new SuccessResponse(dto);
    }

    // 게시글 수정
    @ApiOperation(
            value = "게시글 수정",
            notes = "특정 게시글 수정 api"
    )
    @PutMapping("/{boardId}")
    public SuccessResponse modifyBoard(@ApiIgnore Principal principal,
                                       @RequestBody BoardModifyRequestDto dto,
                                       @Parameter(name = "boardId", description = "게시판 아이디")
                                       @PathVariable Long boardId) {
        boardService.modifyBoard(principal.getName(), dto, boardId);
        return SuccessResponse.ok("modify success");
    }

    // 게시글 삭제
    @ApiOperation(
            value = "게시글 삭제",
            notes = "특정 게시글 삭제 api"
    )
    @DeleteMapping("/{boardId}")
    public SuccessResponse deleteBoard(@ApiIgnore Principal principal,
                                       @Parameter(name = "boardId", description = "게시판 아이디")
                                       @PathVariable(value = "boardId", required = true) Long boardId) {
        boardService.deleteBoard(principal.getName(), boardId);

        return SuccessResponse.ok("delete Success");
    }
}