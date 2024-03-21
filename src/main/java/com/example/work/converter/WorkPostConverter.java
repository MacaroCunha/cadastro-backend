package com.example.work.converter;

import com.example.work.dto.request.WorkRequest;
import com.example.work.model.WorkModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WorkPostConverter implements Converter<WorkRequest, WorkModel> {
    @Override
    public WorkModel convert(WorkRequest workRequest) {

        return WorkModel.builder()
                .workName(workRequest.getWorkName())
                .workDescription(workRequest.getWorkDescription())
                .publicationDate(workRequest.getPublicationDate())
                .exhibitionDate(workRequest.getExhibitionDate())
                .build();
    }
}