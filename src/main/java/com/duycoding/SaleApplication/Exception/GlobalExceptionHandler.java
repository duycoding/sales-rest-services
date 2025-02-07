package com.duycoding.SaleApplication.Exception;

import com.duycoding.SaleApplication.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse> handlingUncaseException(RuntimeException exception) {
        ApiResponse res = new ApiResponse<>();
        res.setStatusCode(400);
        res.setMessage(exception.getMessage());
        res.setError("Not found a buyer");
        return ResponseEntity.badRequest().body(res);
    }

}
