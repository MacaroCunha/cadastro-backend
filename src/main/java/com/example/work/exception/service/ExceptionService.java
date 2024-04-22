package com.example.work.exception.service;

import com.example.work.exception.BusinessException;
import com.example.work.exception.exceptionDto.ExceptionDTO;
import org.springframework.stereotype.Service;

@Service
public class ExceptionService {
    public ExceptionDTO businessException(BusinessException ex) {
        return ExceptionDTO.builder()
                .erro(ex.getMessage())
                .build();
    }

    public ExceptionDTO exception(Exception ex) {
        return ExceptionDTO.builder()
                .erro("Protocolo: Internal Server Error")
                .build();
    }
}