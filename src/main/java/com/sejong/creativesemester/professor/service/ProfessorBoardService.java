package com.sejong.creativesemester.professor.service;

import com.sejong.creativesemester.common.format.exception.user.NotFoundUserException;
import com.sejong.creativesemester.professor.dto.ProfessorListResponseDto;
import com.sejong.creativesemester.professor.dto.ProfessorSimpleResponseDto;
import com.sejong.creativesemester.professor.entity.Professor;
import com.sejong.creativesemester.professor.repository.ProfessorRepository;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfessorBoardService {

    private final UserRepository userRepository;
    private final ProfessorRepository professorRepository;
    private final int TOTAL_ITEMS_PER_PAGE = 20;

    public ProfessorListResponseDto getBoards(String studentNum, int page){
        User user = userRepository.findByStudentNum(studentNum).orElseThrow(NotFoundUserException::new);
        Page<Professor> list = professorRepository.findAllByOrderByName(user.getMajor().getId(), PageRequest.of(page, TOTAL_ITEMS_PER_PAGE));

        return ProfessorListResponseDto.builder()
                .totalPage(list.getTotalPages())
                .currentPage(list.getNumber())
                .list(list.getContent().stream().map((professor -> ProfessorSimpleResponseDto.builder()
                        .boardId(professor.getId())
                        .name(professor.getName())
                        .intro(professor.getIntro())
                        .image(professor.getImage())
                        .build()))
                        .collect(Collectors.toList()))
                .build();
    }
}
