package com.abc.crm.controller.dto.res;

import lombok.Getter;

public enum CodeMessage {

    SUCCESS("0000", "SUCCESS"),
    FAIL("0001", "FAIL"),
    ERROR("9000", "ERROR");

    CodeMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Getter
    private String code;
    @Getter
    private String message;

}
