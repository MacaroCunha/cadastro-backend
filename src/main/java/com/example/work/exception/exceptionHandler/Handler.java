package com.example.work.exception.exceptionHandler;

import com.example.work.exception.BusinessException;
import com.example.work.exception.service.ExceptionService;
import com.example.work.exception.exceptionDto.ExceptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Handler {
    @Autowired
    ExceptionService service;
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionDTO> handleException(BusinessException ex) {
        return new ResponseEntity<>(
                service.businessException(ex),
                HttpStatus.BAD_REQUEST);
    }
}
