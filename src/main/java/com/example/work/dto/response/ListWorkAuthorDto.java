package com.example.work.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListWorkAuthorDto {

    private Long authorId;
    private String authorName;
    private List<WorkDto> works;
}