package com.sejong.creativesemester.chat.service;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "전송 메시지 정보 DTO")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
 
    @Schema
    @NotNull
    private String senderStudentNum;
    @NotBlank
    private String content;

    public void setContent(String content) {
        this.content = content;
    }
}
