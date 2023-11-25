package com.sejong.creativesemester.chat.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    @NotNull
    private Long roomId;
    @NotNull
    private String senderStudentNum;
    @NotBlank
    private String content;

    public void setContent(String content) {
        this.content = content;
    }
}
