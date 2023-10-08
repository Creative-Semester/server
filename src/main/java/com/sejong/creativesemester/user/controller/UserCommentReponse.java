package com.sejong.creativesemester.user.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.joda.time.LocalDateTime;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserCommentReponse {
    private int totalPage;
    private int currentPage;
    @JsonFormat(pattern = "YYYY-MM-DD hh:mm")
    private List<UserCommentDto> comment;
}
