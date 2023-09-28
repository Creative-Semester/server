package com.sejong.creativesemester.comment.controller.req;

import com.sejong.creativesemester.comment.service.req.AddCommentRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Schema
@Getter
public class AddCommentRequest {

    @NotBlank(message = "댓글 내용을 입력해주세요")
    private String text;

    public AddCommentRequestDto toRequestDto() {
        return AddCommentRequestDto.builder()
                .text(this.text)
                .build();
    }
}
