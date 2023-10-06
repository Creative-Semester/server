package com.sejong.creativesemester.professor.controller;

import com.sejong.creativesemester.common.format.success.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/professor")
public class ProfessorBoardController {

    // 교수 조회, 강의 게시판 조회, 학생들 평가 생성, 조회, 삭제, 신고(?)
    @GetMapping()
    public SuccessResponse getProfessorBoards(@ApiIgnore Principal principal,
                                              @RequestParam(required = false, defaultValue = "0", value = "page") int page){

    }

}
