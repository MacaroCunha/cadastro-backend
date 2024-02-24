package com.example.work.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class WorkDto {

    private Long id;
    private String workName;
    private String workDescription;
    private Date publicationDate;
    private Date exhibitionDate;
}



