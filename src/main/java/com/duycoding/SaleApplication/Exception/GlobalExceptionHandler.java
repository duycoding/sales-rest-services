package com.duycoding.SaleApplication.Exception;

import com.duycoding.SaleApplication.Exception.CustomException.BuyerNotFoundException;
import com.duycoding.SaleApplication.Exception.CustomException.GoodsNotFoundException;
import com.duycoding.SaleApplication.Exception.CustomException.InsufficientStockException;
import com.duycoding.SaleApplication.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Xử lý lỗi khi không tìm thấy người mua
    @ExceptionHandler(BuyerNotFoundException.class)
    public ResponseEntity<?> handleBuyerNotFound(BuyerNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
    }

    // Xử lý lỗi khi không tìm thấy hàng hóa
    @ExceptionHandler(GoodsNotFoundException.class)
    public ResponseEntity<?> handleGoodsNotFound(GoodsNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
    }

    // Xử lý lỗi khi không đủ hàng tồn kho
    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<?> handleInsufficientStock(InsufficientStockException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
    }

    // Xử lý tất cả các RuntimeException (có thể là lỗi logic, lỗi database, ...)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntimeException(RuntimeException ex) {
        ApiResponse<Object> res = new ApiResponse<>();
        res.setStatusCode(400);
        res.setMessage("A runtime error occurred");
        res.setError(ex.getMessage());
        return ResponseEntity.badRequest().body(res);
    }

    // Xử lý tất cả các Exception chưa được xử lý
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex) {
        ApiResponse<Object> res = new ApiResponse<>();
        res.setStatusCode(500);
        res.setMessage("Internal Server Error");
        res.setError(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }
}
