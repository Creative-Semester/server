package com.sejong.creativesemester.user.service;

import com.sejong.creativesemester.board.entity.Board;
import com.sejong.creativesemester.board.repository.BoardRepositoryCustom;
import com.sejong.creativesemester.comment.repository.CommentRepositoryCustom;
import com.sejong.creativesemester.common.format.exception.user.NotFoundUserException;
import com.sejong.creativesemester.file.service.dto.res.ImageInfoResponseDto;
import com.sejong.creativesemester.user.controller.UserCommentResponseDto;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {
    private final BoardRepositoryCustom boardRepositoryCustom;
    private final CommentRepositoryCustom commentRepositoryCustom;
    private final UserRepository userRepository;
    private final int TOTAL_ITEMS_PER_PAGE = 20;

    public UserBoardResponseDto getMyBoards(String studentNum, int page) {
        Page<Board> findByStudentNumDesc = boardRepositoryCustom
                .findAllByStudentNumDesc(studentNum, PageRequest.of(page, TOTAL_ITEMS_PER_PAGE));

        List<UserBoardDto> collect = findByStudentNumDesc.stream().map(board ->
                UserBoardDto.builder()
                        .boardId(board.getId())
                        .boardType(board.getBoardType())
                        .title(board.getTitle())
                        .images(board.getFiles().stream()
                                .map(image -> ImageInfoResponseDto.builder()
                                        .imageName(image.getFileName())
                                        .imageUrl(image.getFileUrl())
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

    public User findUser(String studentNum) {
        return userRepository.findByStudentNum(studentNum).orElseThrow(NotFoundUserException::new);
    }

    public Page<UserCommentResponseDto> getMyComment(String studentNum, int page) {
        return commentRepositoryCustom.findAllByStudentNumDesc(studentNum
                , PageRequest.of(page, TOTAL_ITEMS_PER_PAGE));
    }

    public UserInfoResponseDto getMyInfo(String studentNum) {
        User findUser = userRepository.findByStudentNum(studentNum).orElseThrow(NotFoundUserException::new);
        return UserInfoResponseDto.builder()
                .name(findUser.getName())
                .majorName(findUser.getMajor().getName())
                .build();
    }

    public User findUserByBoardId(Long boardId) {
        return userRepository.findByBoardId(boardId);
    }
}
