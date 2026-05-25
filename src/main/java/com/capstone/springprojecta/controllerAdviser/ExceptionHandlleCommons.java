package com.capstone.springprojecta.controllerAdviser;

import com.capstone.springprojecta.dto.ErrorResponseDto;
import com.capstone.springprojecta.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlleCommons {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> handleArthematicException(){
        return new ResponseEntity<>("Something went wrong",HttpStatusCode.valueOf(200));
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> productNotFoundHandler(ProductNotFoundException ex) {
        ErrorResponseDto error = new ErrorResponseDto();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimestamp(LocalDateTime.now());
        error.setError("NOT_FOUND");
        error.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> illeagualArgumentHandler(IllegalArgumentException ex){
        ErrorResponseDto error=new ErrorResponseDto();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        error.setTimestamp(LocalDateTime.now());
        error.setError("BAD_REQUEST");
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> catchAllExceptions(Exception ex){
        ErrorResponseDto error=new ErrorResponseDto();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(ex.getMessage());
        error.setTimestamp(LocalDateTime.now());
        error.setError("INTERNAL_SERVER_ERROR");
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
