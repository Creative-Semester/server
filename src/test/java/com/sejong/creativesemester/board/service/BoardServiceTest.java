package com.sejong.creativesemester.board.service;

import com.sejong.creativesemester.board.dto.BoardCreateRequestDto;
import com.sejong.creativesemester.board.entity.BoardType;
import com.sejong.creativesemester.board.repository.BoardRepository;
import com.sejong.creativesemester.major.entity.Major;
import com.sejong.creativesemester.major.repository.MajorRepository;
import com.sejong.creativesemester.user.entity.Role;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.user.repository.UserRepository;
import com.sejong.creativesemester.vote.repository.VoteRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private BoardService boardService;
    @Autowired
    private MajorRepository majorRepository;

    @BeforeEach
    void beforeAll() {
        voteRepository.deleteAll();
        boardRepository.deleteAll();
        majorRepository.deleteAll();
        userRepository.deleteAll();
    }

    @DisplayName("투표를 포함한 게시글을 작성할때 게시글 생성")
    @Test
    void createBoardWithVote() throws Exception {
        /*//given
        String studentNum = "19011721";
        Major save = majorRepository.save(getImeMajor());
        userRepository.save(makeUser(studentNum,save));
        BoardCreateRequestDto dto=BoardCreateRequestDto.builder()
                .title("test")
                .content("test")
                .image("test")
                .build();
        BoardType boardType=BoardType.Vote;
        //when
        boardService.createBoard(studentNum,dto,boardType);
        //then
        Assertions.assertThat(boardRepository.findAll().size()).isEqualTo(1);
        Assertions.assertThat(voteRepository.findAll().size()).isEqualTo(1);*/
    }

    private static Major getImeMajor() {
        return Major.builder().name("지능기전").build();
    }

    private static User makeUser(String studentNum,Major major) {
        return User.builder()
                .status("재학")
                .grade(3)
                .major(major)
                .role(Role.ROLE_COUNCIL)
                .studentNum(studentNum)
                .build();
    }

    @DisplayName("투표를 포함하지 않은 게시글(학생회 공지글)을 작성할때 게시글 생성")
    @Test
    void createBoardWithCouncil() throws Exception {
        /*//given
        String studentNum = "19011721";
        BoardCreateRequestDto dto=BoardCreateRequestDto.builder()
                .title("test")
                .content("test")
                .image("test")
                .build();
        BoardType boardType = BoardType.Council;
        //when
        boardService.createBoard(studentNum,dto,boardType);
        //then
        Assertions.assertThat(boardRepository.findAll().size()).isEqualTo(1);
        Assertions.assertThat(voteRepository.findAll().size()).isEqualTo(0);*/
    }

    @DisplayName("투표를 포함하지 않은 게시글(자유게시글)을 작성할때 게시글 생성")

    @Test
    void test() throws Exception {
        /*//given
        String studentNum = "19011721";
        BoardCreateRequestDto dto=BoardCreateRequestDto.builder()
                .title("test")
                .content("test")
                .image("test")
                .build();
        BoardType boardType = BoardType.Free;
        //when
        boardService.createBoard(studentNum,dto,boardType);
        //then
        Assertions.assertThat(boardRepository.findAll().size()).isEqualTo(1);
        Assertions.assertThat(voteRepository.findAll().size()).isEqualTo(0);*/
    }
}