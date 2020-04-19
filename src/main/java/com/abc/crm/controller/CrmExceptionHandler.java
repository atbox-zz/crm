package com.abc.crm.controller;

import com.abc.crm.controller.dto.res.ResOneDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CrmExceptionHandler {

    @ExceptionHandler({Exception.class})
    public final ResponseEntity<ResOneDto> handleDemoException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body(ResOneDto.error());
    }
}
