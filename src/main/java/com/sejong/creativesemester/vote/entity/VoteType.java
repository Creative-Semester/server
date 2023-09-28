package com.sejong.creativesemester.vote.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VoteType {
    AGREE("AGREE"),OPPOSE("OPPOSE");
    private final String type;
}
