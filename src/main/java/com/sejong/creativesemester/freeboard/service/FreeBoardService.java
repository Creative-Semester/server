package com.sejong.creativesemester.freeboard.service;

import com.sejong.creativesemester.freeboard.dto.FreeBoardCreateRequestDto;
import com.sejong.creativesemester.freeboard.dto.FreeBoardDetailResponseDto;
import com.sejong.creativesemester.freeboard.dto.FreeBoardResponseDto;
import com.sejong.creativesemester.freeboard.entity.FreeBoard;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.freeboard.repository.FreeBoardRepository;
import com.sejong.creativesemester.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FreeBoardService {

    private final int TOTAL_ITEMS_PER_PAGE = 20;
    private final FreeBoardRepository freeBoardRepository;
    private final UserRepository userRepository;

    // 게시글 추가
    public void createFreeBoard(Long userId, FreeBoardCreateRequestDto dto){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 학생입니다."));

        FreeBoard entity = FreeBoard.builder()
                .user(user)
                .title(dto.getTitle())
                .content(dto.getContent())
                .image(dto.getImage())
                .major(user.getMajor())
                .build();

        freeBoardRepository.save(entity);

    }

    // 게시글 조회
    @Transactional(readOnly = true)
    public FreeBoardResponseDto getFreeBoards(Pageable pageable, int page, long majorId){
        Page<FreeBoard> freeBoardPage = freeBoardRepository.findAllByMajorByCreatedDate(Long.valueOf(majorId)
        , PageRequest.of(page, TOTAL_ITEMS_PER_PAGE));

        return FreeBoardResponseDto.builder()
                .totalPages(freeBoardPage.getTotalPages())
                .currentPage(freeBoardPage.getNumber())
                .freeboards(freeBoardPage.getContent().stream().map((freeBoard) -> FreeBoardDetailResponseDto.builder()
                        .title(freeBoard.getTitle())
                        .content(freeBoard.getContent())
                        .image(freeBoard.getImage())
                        .build()).collect(Collectors.toList()))
                        .build();
    }

    // 게시글 상세 조회
    @Transactional(readOnly = true)
    public FreeBoardDetailResponseDto getDetailFreeBoards(Long freeBoardId){
        FreeBoard freeBoard = freeBoardRepository.findById(freeBoardId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));

        return FreeBoardDetailResponseDto.builder()
                .title(freeBoard.getTitle())
                .content(freeBoard.getContent())
                .image(freeBoard.getImage())
                .build();
    }

}