package com.sejong.creativesemester.comment.service.req;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddCommentRequestDto {
    private String text;
}
