package com.sejong.creativesemester.board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardType {
    Free("Free"),Council("Council"), Vote("Vote");
    private final String type;
}
