package com.sejong.creativesemester.board.controller;

import com.sejong.creativesemester.board.dto.BoardCreateRequestDto;
import com.sejong.creativesemester.board.entity.BoardType;
import com.sejong.creativesemester.board.service.CouncilBoardService;
import com.sejong.creativesemester.common.format.success.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/api/v1/council")
@RestController
public class CouncilBoardController {

    private final CouncilBoardService councilBoardService;
    @ApiOperation(
            value = "게시글 생성",
            notes = "자유 게시판의 게시글 생성 api"
    )
    @PostMapping("/boards")
    public SuccessResponse createCouncilBoard(@RequestParam String studentNum, @RequestBody final BoardCreateRequestDto dto
            , @Parameter(name = "게시판 종류",required = true,
            schema = @Schema(
                    type = "string",
                    allowableValues = {"Council","Vote"}),
            in = ParameterIn.QUERY) @RequestParam BoardType boardType){
        councilBoardService.createCouncilBoard(studentNum, dto,boardType);
        return SuccessResponse.ok();
    }
}