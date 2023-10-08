package com.sejong.creativesemester.user.service;

import com.sejong.creativesemester.board.entity.Board;
import com.sejong.creativesemester.board.repository.BoardRepositoryCustom;
import com.sejong.creativesemester.comment.repository.CommentRepositoryCustom;
import com.sejong.creativesemester.image.service.dto.res.ImageInfoResponseDto;
import com.sejong.creativesemester.user.controller.UserCommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final BoardRepositoryCustom boardRepositoryCustom;
    private final CommentRepositoryCustom commentRepositoryCustom;
    private final int TOTAL_ITEMS_PER_PAGE = 20;

    public UserBoardResponseDto getMyBoards(String studentNum, int page) {
        Page<Board> findByStudentNumDesc = boardRepositoryCustom
                .findAllByStudentNumDesc(studentNum, PageRequest.of(page, TOTAL_ITEMS_PER_PAGE));

        List<UserBoardDto> collect = findByStudentNumDesc.stream().map(board ->
                UserBoardDto.builder()
                        .boardId(board.getId())
                        .boardType(board.getBoardType())
                        .title(board.getTitle())
                        .images(board.getImages().stream()
                                .map(image -> ImageInfoResponseDto.builder()
                                        .imageName(image.getImageName())
                                        .imageUrl(image.getImageUrl())
                                        .build()).collect(Collectors.toList()))
                        .content(board.getContent())
                        .createdTime(board.getCreatedTime())
                        .build()).collect(Collectors.toList());
        return UserBoardResponseDto.builder()
                .totalPage(findByStudentNumDesc.getTotalPages())
                .currentPage(findByStudentNumDesc.getNumber())
                .boardDtoList(collect)
                .build();


    }

    public Page<UserCommentResponseDto> getMyComment(String studentNum, int page) {
        return commentRepositoryCustom.findAllByStudentNumDesc(studentNum
                , PageRequest.of(page, TOTAL_ITEMS_PER_PAGE));
    }
}
