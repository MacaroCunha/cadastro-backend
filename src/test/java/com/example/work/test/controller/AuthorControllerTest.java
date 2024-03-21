package com.example.work.test.controller;

import com.example.work.dto.response.AuthorDto;
import com.example.work.exception.AuthorException;
import com.example.work.controller.AuthorController;
import com.example.work.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class AuthorControllerTest {

    @Mock
    private AuthorService authorService;
    @InjectMocks
    private AuthorController authorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAuthorById_NonExistingId_ReturnsNotFoundStatus() {
        long nonExistingId = 999L; // Assumindo que este ID não existe
        when(authorService.getAuthorById(nonExistingId)).thenReturn(null); // Simulando um autor que não existe
        ResponseEntity<AuthorDto> responseEntity = authorController.getAuthorById(nonExistingId);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAuthorById_NonExistingId_ThrowsResponseStatusException() {
        // Arrange
        long nonExistingId = 999L; // Assumindo que este ID não existe
        when(authorService.getAuthorById(nonExistingId)).thenThrow(new AuthorException("Author not found"));
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            authorController.getAuthorById(nonExistingId);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Author not found", exception.getReason());
    }
}
