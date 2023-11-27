package com.sejong.creativesemester.note.controller.res;

import lombok.Getter;

import java.util.List;

@Getter
public class FindNoteInBoardWithUserResponse {
    private Long boardId;
    private List<NoteInBoardWithUserResponse> note;
}
