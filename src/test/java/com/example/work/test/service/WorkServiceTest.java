package com.example.work.test.service;
import com.example.work.converter.WorkConverter;
import com.example.work.converter.WorkPostConverter;
import com.example.work.dto.request.WorkRequest;
import com.example.work.dto.response.WorkDto;
import com.example.work.model.WorkModel;
import com.example.work.repository.WorkRepository;
import com.example.work.service.WorkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WorkServiceTest {
    @Mock
    private WorkRepository workRepository;
    @Mock
    private WorkPostConverter workPostConverter;
    @Mock
    private WorkConverter workConverter;
    @InjectMocks
    private WorkService workService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllWorks_WhenWorksExist_ReturnsWorks() {
        WorkModel mockWork = new WorkModel();
        when(workRepository.findAll()).thenReturn(Collections.singletonList(mockWork));
        when(workConverter.toDto(mockWork)).thenReturn(
                WorkDto.builder()
                        .id(1L)
                        .workName("Example Work")
                        .workDescription("Example Description")
                        .publicationDate(new Date())
                        .exhibitionDate(new Date())
                        .build()
        );
        var result = workService.getAllWorks();
        assertFalse(result.isEmpty());
        verify(workRepository, times(1)).findAll();
        verify(workConverter, times(1)).toDto(mockWork);
    }
    @Test
    public void testGetWorkById_WhenWorkExists_ReturnsWork() {
        long id = 1L;
        WorkModel mockWork = new WorkModel();
        when(workRepository.findById(id)).thenReturn(Optional.of(mockWork));
        when(workConverter.toDto(mockWork)).thenReturn(
                WorkDto.builder()
                        .id(id)
                        .workName("Example Work")
                        .workDescription("Example Description")
                        .publicationDate(new Date())
                        .exhibitionDate(new Date())
                        .build()
        );
        Optional<WorkDto> result = workService.getWorkById(id);
        assertTrue(result.isPresent());
        verify(workRepository, times(1)).findById(id);
        verify(workConverter, times(1)).toDto(mockWork);
    }
    @Test
    public void testCreateWork_WhenValidRequest_ReturnsCreatedWork() {
        WorkRequest request = new WorkRequest();
        WorkModel newWorkModel = new WorkModel();
        WorkDto newWorkDto =
                WorkDto.builder()
                        .id(1L)
                        .workName("Example Work")
                        .workDescription("Example Description")
                        .publicationDate(new Date())
                        .exhibitionDate(new Date())
                        .build();
        when(workPostConverter.convert(request)).thenReturn(newWorkModel);
        when(workRepository.save(newWorkModel)).thenReturn(newWorkModel);
        when(workConverter.toDto(newWorkModel)).thenReturn(newWorkDto);
        WorkDto result = workService.createWork(request);
        assertNotNull(result);
        verify(workPostConverter, times(1)).convert(request);
        verify(workRepository, times(1)).save(newWorkModel);
        verify(workConverter, times(1)).toDto(newWorkModel);
    }
}