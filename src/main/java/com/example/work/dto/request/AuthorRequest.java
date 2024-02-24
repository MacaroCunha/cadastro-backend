package com.example.work.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class AuthorRequest {
    private String cpf;
    private String name;
    private String countryOfOrigin;
    private String gender;
    private String email;
    private Date birthDate;
}


