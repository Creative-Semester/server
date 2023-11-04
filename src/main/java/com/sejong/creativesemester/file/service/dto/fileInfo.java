package com.sejong.creativesemester.file.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "이미지 정보 응답값")
@AllArgsConstructor
@Builder
@Getter
public class fileInfo {
    @Schema(description = "파일 이름, 해당 이미지 이름")
    private String fileName;
    @Schema(description = "이미지 url, 해당값이 이미지를 보여줌")
    private String fileUrl;
}
