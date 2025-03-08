package com.websolution.api.common.exception;

import com.websolution.api.common.response.BaseResponse;
import com.websolution.api.common.response.BaseResponseStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // RuntimeException 예외 처리
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BaseResponse<String>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.badRequest().body(new BaseResponse<>(BaseResponseStatus.BAD_REQUEST, ex.getMessage()));
    }
}