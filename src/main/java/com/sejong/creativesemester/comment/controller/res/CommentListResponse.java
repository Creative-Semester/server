package com.sejong.creativesemester.comment.controller.res;

import com.sejong.creativesemester.comment.service.res.CommentInfoDto;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@Builder
public class CommentListResponse {
    private Page<CommentInfoDto> commentList;
}
