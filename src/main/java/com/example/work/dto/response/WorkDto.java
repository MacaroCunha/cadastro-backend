package com.example.work.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)

public class WorkDto {
    private Long id;
    private String workName;
    private String workDescription;
    private Date publicationDate;
    private Date exhibitionDate;
}

