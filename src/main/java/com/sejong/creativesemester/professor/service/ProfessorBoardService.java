package com.sejong.creativesemester.professor.service;

import com.sejong.creativesemester.common.format.exception.user.NotFoundUserException;
import com.sejong.creativesemester.professor.dto.ProfessorListResponseDto;
import com.sejong.creativesemester.professor.dto.ProfessorSimpleResponseDto;
import com.sejong.creativesemester.professor.repository.ProfessorRepository;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfessorBoardService {

    private final UserRepository userRepository;
    private final ProfessorRepository professorRepository;

    public ProfessorListResponseDto getBoards(String studentNum, int page){
        User user = userRepository.findByStudentNum(studentNum).orElseThrow(NotFoundUserException::new);
        Page<ProfessorSimpleResponseDto> list = professorRepository.
    }
}
