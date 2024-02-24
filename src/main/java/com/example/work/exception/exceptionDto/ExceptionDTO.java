package com.example.work.exception.exceptionDto;



import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionDTO {
    private final String field;
    private final String error_description;
}