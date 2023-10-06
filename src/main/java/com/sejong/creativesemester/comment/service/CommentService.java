package com.sejong.creativesemester.comment.service;

import com.sejong.creativesemester.board.entity.Board;
import com.sejong.creativesemester.board.repository.BoardRepository;
import com.sejong.creativesemester.comment.entity.Comment;
import com.sejong.creativesemester.comment.repository.CommentRepository;
import com.sejong.creativesemester.comment.repository.CommentRepositoryCustom;
import com.sejong.creativesemester.comment.repository.dto.CommentListDto;
import com.sejong.creativesemester.comment.service.req.AddCommentRequestDto;
import com.sejong.creativesemester.comment.service.res.CommentInfoDto;
import com.sejong.creativesemester.common.format.exception.board.NotFoundBoardException;
import com.sejong.creativesemester.common.format.exception.user.NotFoundUserException;
import com.sejong.creativesemester.common.meta.DistributeLock;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Transactional
@RequiredArgsConstructor
@Service
public class CommentService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final CommentRepositoryCustom commentRepositoryCustom;
    private static final String COMMENT_KEY = "COMMENT_";
    private final static int TOTAL_CONTENT_PER_PAGE = 20;

    public void addComment(Long boardId, String studentNum, AddCommentRequestDto addCommentRequestDto) {
        Board boardById = boardRepository.findById(boardId).orElseThrow(NotFoundBoardException::new);
        User userByStudentNum = userRepository.findByStudentNum(studentNum).orElseThrow(NotFoundUserException::new);
        Comment saveComment = commentRepository.save(Comment.builder()
                .text(addCommentRequestDto.getText())
                .board(boardById)
                .user(userByStudentNum)
                .build());
        saveComment.getUser().addComment(saveComment);

    }

    public void deleteComment(String studentNum, Long commentId) {
        Comment commentById = commentRepository.findById(commentId).orElseThrow(
                () -> new NullPointerException("없는 댓글입니다.")
        );
        commentRepository.delete(commentById);
    }

    @DistributeLock(identifier = COMMENT_KEY, key = "#commentId")
    public void reportComment(String studentNum, Long commentId) {
        Comment commentById = commentRepository.findById(commentId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 댓글입니다.")
        );
        commentById.reportComment();
    }

    public CommentInfoResponseDto getCommentList(String studentNum, Long boardId, int page) {
        Page<CommentListDto> allCommentOfBoard = commentRepositoryCustom.findAllCommentOfBoard(
                boardId, PageRequest.of(page, TOTAL_CONTENT_PER_PAGE));
        List<CommentInfoDto> commentInfoDto = new ArrayList<>();
        for (CommentListDto commentListDto : allCommentOfBoard) {
            Boolean isMine = FALSE;
            if (commentListDto.getStudentNum().equals(studentNum)) {
                isMine = TRUE;
            }
            commentInfoDto.add(CommentInfoDto.builder()
                    .id(commentListDto.getId())
                    .text(commentListDto.getText())
                    .createdTime(commentListDto.getCreatedTime())
                    .isMine(isMine)
                    .build());
        }
        return CommentInfoResponseDto.builder()
                .commentInfoDtoList(commentInfoDto)
                .totalPage(allCommentOfBoard.getTotalPages())
                .currentPage(allCommentOfBoard.getNumber())
                .build();
    }

}
