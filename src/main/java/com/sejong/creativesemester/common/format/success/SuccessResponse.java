package com.sejong.creativesemester.common.format.success;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Builder
@ToString
@Getter
@AllArgsConstructor
@JsonPropertyOrder({"time","status","code","message","result"})
public class SuccessResponse<T> {
    @JsonProperty("status")
    private int status;
    private LocalDateTime time;
    private String code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    public SuccessResponse(T result) {
        this.result = result;
        this.status = HttpStatus.OK.value();
        this.time = now();
        this.code = SuccessResponseStatus.SUCCESS.getCode();
        this.message=SuccessResponseStatus.SUCCESS.getMessage();
    }
    public SuccessResponse(T result,String message) {
        this.result = result;
        this.status = HttpStatus.OK.value();
        this.time = now();
        this.code = SuccessResponseStatus.SUCCESS.getCode();
        this.message = message;
    }

    // 단순히 성공메시지 보낼때
    public static SuccessResponse ok(){
        return SuccessResponse.builder()
                .status(HttpStatus.OK.value())
                .time(now())
                .code(SuccessResponseStatus.SUCCESS.getCode())
                .message("SUCCESS")
                .build();
    }


    // 성공후 메시지를 설정하여 보내고 싶을때
    public static SuccessResponse ok(String message){
        return SuccessResponse.builder()
                .status(HttpStatus.OK.value())
                .time(now())
                .code(SuccessResponseStatus.SUCCESS.getCode())
                .message(message)
                .build();
    }

}



