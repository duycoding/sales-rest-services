package com.duycoding.SaleApplication.Exception;

import com.duycoding.SaleApplication.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class) // or @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handlingUncaseException(Exception exception) {
        ApiResponse<Object> res = new ApiResponse<>();
        res.setStatusCode(400);
        res.setMessage(exception.getMessage());
        res.setError("Fix error now");
        return ResponseEntity.badRequest().body(res);
    }
}
