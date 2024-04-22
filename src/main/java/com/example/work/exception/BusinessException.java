package com.example.work.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BusinessException extends RuntimeException {

    private String codigo;
    public BusinessException(String message) {
        super(message);
    }
}