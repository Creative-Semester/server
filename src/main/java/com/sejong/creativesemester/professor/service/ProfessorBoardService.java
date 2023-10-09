package com.sejong.creativesemester.professor.service;

import com.sejong.creativesemester.board.entity.Board;
import com.sejong.creativesemester.comment.entity.Comment;
import com.sejong.creativesemester.comment.service.req.AddCommentRequestDto;
import com.sejong.creativesemester.common.format.exception.board.NotFoundBoardException;
import com.sejong.creativesemester.common.format.exception.professor.NotFoundCourseException;
import com.sejong.creativesemester.common.format.exception.professor.NotMatchProfessorException;
import com.sejong.creativesemester.common.format.exception.user.NotFoundUserException;
import com.sejong.creativesemester.professor.dto.*;
import com.sejong.creativesemester.professor.entity.Course;
import com.sejong.creativesemester.professor.entity.Evaluation;
import com.sejong.creativesemester.professor.entity.Professor;
import com.sejong.creativesemester.professor.repository.CourseRepository;
import com.sejong.creativesemester.professor.repository.EvaluationRepository;
import com.sejong.creativesemester.professor.repository.ProfessorRepository;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfessorBoardService {

    private final UserRepository userRepository;
    private final ProfessorRepository professorRepository;
    private final CourseRepository courseRepository;
    private final EvaluationRepository evaluationRepository;
    private final int TOTAL_ITEMS_PER_PAGE = 20;

    public ProfessorListResponseDto getBoards(String studentNum, int page){
        User user = userRepository.findByStudentNum(studentNum).orElseThrow(NotFoundUserException::new);
        Page<Professor> list = professorRepository.findAllByOrderByName(user.getMajor().getId(), PageRequest.of(page, TOTAL_ITEMS_PER_PAGE));

        return ProfessorListResponseDto.builder()
                .totalPage(list.getTotalPages())
                .currentPage(list.getNumber())
                .list(list.getContent().stream().map((professor -> ProfessorListSimpleResponseDto.builder()
                        .boardId(professor.getId())
                        .name(professor.getName())
                        .intro(professor.getIntro())
                        .image(professor.getImage())
                        .build()))
                        .collect(Collectors.toList()))
                .build();
    }

    public CourseListResponseDto getCourseBoards(Long professorId, String studentNum,  int page){
        User user = userRepository.findByStudentNum(studentNum).orElseThrow(NotFoundUserException::new);

        Page<Course> coursePage = courseRepository.findAllByOrderByName(professorId,
                PageRequest.of(page, TOTAL_ITEMS_PER_PAGE));

        Professor professor = professorRepository.findById(professorId).orElseThrow(NotMatchProfessorException::new);

        return CourseListResponseDto.builder()
                .totalPage(coursePage.getTotalPages())
                .currentPage(coursePage.getNumber())
                .list(coursePage.getContent().stream().map((course) -> CourseSimpleResponseDto.builder()
                        .title(course.getTitle())
                        .classification(course.getClassification())
                        .grade(course.getGrade())
                        .score(course.getScore())
                        .build()).collect(Collectors.toList())
                )
                .professorSimpleResponseDto(ProfessorSimpleResponseDto.builder()
                        .name(professor.getName())
                        .image(professor.getImage())
                        .location(professor.getLocation())
                        .phoneNum(professor.getPhonenum())
                        .email(professor.getEmail())
                        .majorSub(professor.getMajorSub())
                        .lab(professor.getLab())
                        .build())
                .build();
    }

    public void addEvaluation(Long professorId, Long courseId, String studentNum, AddCommentRequestDto addCommentRequestDto) {

        User userByStudentNum = userRepository.findByStudentNum(studentNum).orElseThrow(NotFoundUserException::new);
        Course course = courseRepository.findById(courseId).orElseThrow(NotFoundCourseException::new);

        if(!(course.getProfessor().getId()).equals(professorId)){
            throw new NotMatchProfessorException();
        }

        Evaluation evaluation = evaluationRepository.save(Evaluation.builder()
                .text(addCommentRequestDto.getText())
                .course(course)
                .user(userByStudentNum)
                .build());

        log.info("text: {}", evaluation.getText());
        evaluation.getUser().addEvaluation(evaluation);
    }

    public EvaluationListResponseDto getEvaluationBoards(Long professorId, Long courseId, String studentNum, int page){

        Course course = courseRepository.findById(courseId).orElseThrow(NotFoundCourseException::new);
        if(!(course.getProfessor().getId()).equals(professorId)){
            throw new NotMatchProfessorException();
        }
        Page<Evaluation> evaluationPage = evaluationRepository.findAllByCreatedTime(courseId, PageRequest.of(page, TOTAL_ITEMS_PER_PAGE));

        return EvaluationListResponseDto.builder()
                .totalPage(evaluationPage.getTotalPages())
                .currentPage(evaluationPage.getNumber())
                .evaluationList(evaluationPage.getContent().stream().map((evaluation) -> EvaluationSimpleResponseDto.builder()
                        .name(evaluation.getUser().getName())
                        .text(evaluation.getText())
                        .createdTime(evaluation.getCreatedTime())
                        .build())
                        .collect(Collectors.toList())).build();
    }
}
