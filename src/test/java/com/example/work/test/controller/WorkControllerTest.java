package com.example.work.test.controller;
import com.example.work.controller.WorkController;
import com.example.work.dto.request.WorkRequest;
import com.example.work.dto.response.WorkDto;
import com.example.work.service.WorkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class WorkControllerTest {

    @Mock
    private WorkService workService;
    @InjectMocks
    private WorkController workController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetAllWorks_WhenWorksExist_ReturnsWorks() {
        WorkDto workDto = WorkDto.builder()
                .id(1L)
                .workName("Example Work")
                .workDescription("Example Description")
                .publicationDate(new Date())
                .exhibitionDate(new Date())
                .build();
        when(workService.getAllWorks()).thenReturn(Collections.singletonList(workDto));
        ResponseEntity<?> responseEntity = workController.getAllWorks();
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testCreateWork_WhenValidRequest_ReturnsCreatedWork() {
        WorkRequest request = new WorkRequest();
        WorkDto createdWorkDto = WorkDto.builder()
                .id(1L)
                .workName("Example Work")
                .workDescription("Example Description")
                .publicationDate(new Date())
                .exhibitionDate(new Date())
                .build();
        when(workService.createWork(request)).thenReturn(createdWorkDto);
        ResponseEntity<?> responseEntity = workController.createWork(request);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testGetWorkById_WhenWorkExists_ReturnsWork() {
        long id = 1L;
        WorkDto workDto = WorkDto.builder()
                .id(id)
                .workName("Example Work")
                .workDescription("Example Description")
                .publicationDate(new Date())
                .exhibitionDate(new Date())
                .build();
        when(workService.getWorkById(id)).thenReturn(Optional.of(workDto));
        ResponseEntity<WorkDto> responseEntity = workController.getWorkById(id);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}