package com.abc.crm.controller.dto.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
@Builder
public class ResOneDto {

    private String message;
    private String code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public static ResOneDto success(Object data) {
        Objects.requireNonNull(data);

        return ResOneDto.builder()
                .data(data)
                .code(CodeMessage.SUCCESS.getCode())
                .message(CodeMessage.SUCCESS.getMessage())
                .build();
    }

    public static ResOneDto success() {
        return ResOneDto.builder()
                .code(CodeMessage.SUCCESS.getCode())
                .message(CodeMessage.SUCCESS.getMessage())
                .build();
    }

    public static ResOneDto empty() {
        return success();
    }

    public static ResOneDto fail() {
        return ResOneDto.builder()
                .code(CodeMessage.FAIL.getCode())
                .message(CodeMessage.FAIL.getMessage())
                .build();
    }

}
