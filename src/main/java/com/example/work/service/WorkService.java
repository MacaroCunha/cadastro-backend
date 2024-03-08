package com.example.work.service;

import com.example.work.converter.WorkConverter;
import com.example.work.dto.WorkDto;
import com.example.work.dto.request.WorkRequest;
import com.example.work.exception.WorkException;
import com.example.work.message.WorkMessage;
import com.example.work.model.WorkModel;
import com.example.work.repository.WorkRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkService {
    private final Logger logger = LoggerFactory.getLogger(WorkService.class);

    @Autowired
    private final WorkRepository workRepository;

    public List<WorkDto> getAllWorks() {
        List<WorkModel> works = workRepository.findAll();
        return works.stream()
                .map(WorkConverter::toDto)
                .collect(Collectors.toList());
    }

    public Optional<WorkDto> getWorkById(Long id) {
        return workRepository.findById(id).map(WorkConverter::toDto);
    }

    @Transactional
    public WorkDto createWork(WorkRequest workRequest) {
        try {
            validateWorkRequest(workRequest);
            WorkModel newWork = WorkConverter.toEntity(workRequest);
            WorkModel savedWork = workRepository.save(newWork);
            logger.info("Work created successfully. ID: {}", savedWork.getId());
            return WorkConverter.toDto(savedWork);
        } catch (Exception e) {
            logger.error("Error creating work", e);
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
                        logger.info("Work updated successfully. ID: {}", updatedWork.getId());
                        return WorkConverter.toDto(updatedWork);
                    })
                    .orElseThrow(() -> new IllegalArgumentException(String.format(WorkMessage.WORK_NOT_FOUND, id)));
        } catch (Exception e) {
            logger.error("Error updating work", e);
            throw new WorkException(WorkMessage.UPDATED_WORK);
        }
    }

    public void deleteWork(Long id) {
        workRepository.findById(id).ifPresentOrElse(
                work -> {
                    workRepository.delete(work);
                    logger.info("Work deleted successfully. ID: {}", id);
                },
                () -> {
                    logger.error("Work not found for deletion. ID: {}", id);
                    throw new IllegalArgumentException(String.format(WorkMessage.WORK_NOT_FOUND, id));
                }
        );
    }

    private void validateWorkRequest(WorkRequest workRequest) {
        // Implement your validation logic here, if needed.
    }

    private void updateEntityFromRequest(WorkModel existingWork, WorkRequest workRequest) {
        existingWork.setWorkName(workRequest.getWorkName());
        existingWork.setWorkDescription(workRequest.getWorkDescription());
        existingWork.setPublicationDate(workRequest.getPublicationDate());
        existingWork.setExhibitionDate(workRequest.getExhibitionDate());
    }
}


