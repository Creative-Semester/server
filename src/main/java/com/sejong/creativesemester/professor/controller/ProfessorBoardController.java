package com.sejong.creativesemester.professor.controller;

import com.sejong.creativesemester.comment.controller.req.AddCommentRequest;
import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.professor.dto.CourseListResponseDto;
import com.sejong.creativesemester.professor.dto.EvaluationListResponseDto;
import com.sejong.creativesemester.professor.dto.ProfessorListResponseDto;
import com.sejong.creativesemester.professor.service.ProfessorBoardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/professor")
public class ProfessorBoardController {

    private final ProfessorBoardService professorBoardService;

    // 교수 조회, 강의 게시판 조회, 학생들 평가 생성, 조회, 삭제, 신고(?)

    @ApiOperation(value = "교수게시판 조회",
    notes = "현재 학과에 해당하는 교수님 목록을 조회합니다.")
    @GetMapping()
    public SuccessResponse<ProfessorListResponseDto> getProfessorBoards(@ApiIgnore Authentication authentication,
                                                                        @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        return new SuccessResponse(professorBoardService.getBoards(authentication.getName(), page));
    }

    @ApiOperation(value = "강의게시판 조회",
    notes = "해당 교수에 해당하는 정보와 강의 목록을 조회합니다.")
    @GetMapping("/{professorId}")
    public SuccessResponse<CourseListResponseDto> getCourseBoards(@ApiIgnore Authentication authentication,
                                                                  @PathVariable(value = "professorId", required = true) Long professorId,
                                                                  @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        return new SuccessResponse(professorBoardService.getCourseBoards(professorId, authentication.getName(), page));
    }

    @ApiOperation(value = "평가댓글 생성 api",
            notes = "강의에 대한 평가댓글을 작성합니다.")
    @PostMapping("/{professorId}/{courseId}")
    public SuccessResponse addEvaluation(@ApiIgnore Principal principal,
            @PathVariable(value = "professorId", required = true) Long professorId,
            @PathVariable(value = "courseId", required = true) Long courseId,
            @Valid @RequestBody AddCommentRequest addCommentRequest) {
        professorBoardService.addEvaluation(professorId, courseId, principal.getName(), addCommentRequest.toRequestDto());
        return SuccessResponse.ok("댓글이 작성되었습니다.");
    }

    @ApiOperation(value = "평가게시글 조회 api",
    notes = "강의에 대한 평가댓글들을 조회합니다.")
    @GetMapping("/{professorId}/{courseId}")
    public SuccessResponse<EvaluationListResponseDto> getEvaluationBoards(@ApiIgnore Authentication authentication,
                                                                          @PathVariable(value = "professorId", required = true) Long professorId,
                                                                          @PathVariable(value = "courseId", required = true) Long courseId,
                                                                          @RequestParam(required = false, defaultValue = "0", value = "page") int page){
        return new SuccessResponse<>(professorBoardService.getEvaluationBoards(professorId, courseId, authentication.getName(), page));
    }

}
