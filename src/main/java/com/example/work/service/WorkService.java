package com.example.work.service;

import com.example.work.dto.WorkDto;
import com.example.work.dto.request.WorkRequest;
import com.example.work.exception.WorkException;
import com.example.work.message.WorkMessage;
import com.example.work.model.WorkModel;
import com.example.work.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkService {

    private final WorkRepository workRepository;

    public List<WorkDto> getAllWorks() {
        List<WorkModel> works = workRepository.findAll();
        return works.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    public Optional<WorkDto> getWorkById(Long id) {
        return workRepository.findById(id).map(this::convertToDto);
    }
    public WorkDto createWork(WorkRequest workRequest) {
        try {
            validateWorkRequest(workRequest);
            WorkModel newWork = convertToEntity(workRequest);
            WorkModel savedWork = workRepository.save(newWork);
            return convertToDto(savedWork);
        } catch (Exception e) {
            throw new WorkException(WorkMessage.CREATED_WORK);
        }
    }
    public WorkDto updateWork(Long id, WorkRequest workRequest) {
        try {
            validateWorkRequest(workRequest);
            return workRepository.findById(id)
                    .map(existingWork -> {
                        updateEntityFromRequest(existingWork, workRequest);
                        WorkModel updatedWork = workRepository.save(existingWork);
                        return convertToDto(updatedWork);
                    })
                    .orElseThrow(() -> new IllegalArgumentException(String.format(WorkMessage.WORK_NOT_FOUND, id)));
        } catch (Exception e) {
            throw new WorkException(WorkMessage.UPDATED_WORK);
        }
    }
    public void deleteWork(Long id) {
        workRepository.findById(id).ifPresentOrElse(
                workRepository::delete,
                () -> {
                    throw new IllegalArgumentException(String.format(WorkMessage.WORK_NOT_FOUND, id));
                }
        );
    }
    private void validateWorkRequest(WorkRequest workRequest) {
        // Add validation logic
    }
    private WorkModel convertToEntity(WorkRequest workRequest) {
        return WorkModel.builder()
                .workName(workRequest.getWorkName())
                .workDescription(workRequest.getWorkDescription())
                .publicationDate(workRequest.getPublicationDate())
                .exhibitionDate(workRequest.getExhibitionDate())
                .build();
    }
    private void updateEntityFromRequest(WorkModel existingWork, WorkRequest workRequest) {
        existingWork.setWorkName(workRequest.getWorkName());
        existingWork.setWorkDescription(workRequest.getWorkDescription());
        existingWork.setPublicationDate(workRequest.getPublicationDate());
        existingWork.setExhibitionDate(workRequest.getExhibitionDate());
    }
    private WorkDto convertToDto(WorkModel workModel) {
        return WorkDto.builder()
                .id(workModel.getId())
                .workName(workModel.getWorkName())
                .workDescription(workModel.getWorkDescription())
                .publicationDate(workModel.getPublicationDate())
                .exhibitionDate(workModel.getExhibitionDate())
                .build();
    }
}