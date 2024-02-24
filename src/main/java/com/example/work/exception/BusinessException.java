package com.example.work.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BusinessException extends RuntimeException {

    private String codigo;
    public BusinessException(String codigo, String message) {
        super(message);
        this.codigo = codigo;
    }
}