package com.example.work.exception.service;

import com.example.work.exception.BusinessException;
import com.example.work.exception.exceptionDto.ExceptionDTO;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExceptionService {
    public ExceptionDTO businessException(BusinessException ex) {
        log.error("Exception {}, erro {}",
                "BusinessException",
                ex.getMessage());
        return ExceptionDTO.builder()
                .field(ex.getCodigo())
                .error_description(ex.getMessage())
                .build();
    }

    public ExceptionDTO exception(Exception ex) {
        log.error("Exception {}, erro {}",
                ex.getLocalizedMessage(),
                ex.getMessage());
        long protocolo = System.currentTimeMillis();
        return ExceptionDTO.builder()
                .field("Exception")
                .error_description("Protocolo: " + protocolo + " Internal Server Error")
                .build();
    }
}
