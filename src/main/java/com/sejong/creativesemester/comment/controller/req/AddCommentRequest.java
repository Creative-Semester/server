package com.sejong.creativesemester.comment.controller.req;

import com.sejong.creativesemester.comment.service.req.AddCommentRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema
@Getter
public class AddCommentRequest {
    private String text;

    public AddCommentRequestDto toRequestDto() {
        return AddCommentRequestDto.builder()
                .text(this.text)
                .build();
    }
}
