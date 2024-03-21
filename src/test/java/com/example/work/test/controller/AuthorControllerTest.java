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
import static org.mockito.Mockito.*;

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
    public void testGetAuthorById_ExistingId_ReturnsAuthor() {
        long id = 1L;
        AuthorDto mockAuthorDto = createMockAuthorDto(id);
        when(authorService.getAuthorById(id)).thenReturn(mockAuthorDto);
        ResponseEntity<AuthorDto> responseEntity = authorController.getAuthorById(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockAuthorDto, responseEntity.getBody());
        verify(authorService, times(1)).getAuthorById(id);
    }
    @Test
    public void testGetAuthorById_NonExistingId_ThrowsNotFoundException() {
        // Arrange
        long id = 2L;
        when(authorService.getAuthorById(id)).thenThrow(new AuthorException("Author not found"));
        ResponseStatusException exception = org.junit.jupiter.api.Assertions.assertThrows(ResponseStatusException.class, () -> {
            authorController.getAuthorById(id);
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode().value());
        assertEquals("Author not found", exception.getReason());
        verify(authorService, times(1)).getAuthorById(id);
    }
    private AuthorDto createMockAuthorDto(long id) {
        return AuthorDto.builder()
                .id(id)
                .name("John Doe")
                .build();
    }
}



