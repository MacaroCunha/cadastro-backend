package com.example.work.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class WorkRequest {
    private String workName;
    private String workDescription;
    private Date publicationDate;
    private Date exhibitionDate;
    private AuthorRequest author;

    @Data
    public static class AuthorRequest {
        private Long id;
    }
}