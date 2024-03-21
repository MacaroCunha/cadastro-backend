package com.example.work.test.service;
import com.example.work.converter.AuthorConverter;
import com.example.work.dto.response.AuthorDto;
import com.example.work.exception.AuthorException;
import com.example.work.model.AuthorModel;
import com.example.work.repository.AuthorRepository;
import com.example.work.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class AuthorServiceTest {
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private AuthorConverter authorConverter;
    @InjectMocks
    private AuthorService authorService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void getAllAuthors_WhenAuthorsExist_ReturnsListOfAuthors() {
        // Arrange
        when(authorRepository.findAll()).thenReturn(Collections.singletonList(new AuthorModel()));
        when(authorConverter.convertToDTO(any(AuthorModel.class))).thenReturn(new AuthorDto());
        var result = authorService.getAllAuthors();
        assertFalse(result.isEmpty());
        verify(authorRepository, times(1)).findAll();
        verify(authorConverter, times(1)).convertToDTO(any(AuthorModel.class));
    }
    @Test
    public void getAllAuthors_WhenNoAuthorsExist_ThrowsException() {
        when(authorRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(AuthorException.class, () -> authorService.getAllAuthors());
    }
//    @Test
//    public void testGetAllAuthors_WhenNoAuthorsExist_ThrowsException() {
//        // Arrange
//        when(authorRepository.findAll()).thenReturn(Collections.emptyList());
//        // Configurar comportamento do mock para lançar uma ResponseStatusException
//        when(authorService.getAllAuthors()).thenThrow(new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum autor encontrado."));
//
//        // Assert
//        assertThrows(ResponseStatusException.class, () -> authorService.getAllAuthors());
//    }

    @Test
    public void getAuthorById_WhenAuthorDoesNotExist_ThrowsException() {
        long id = 1L;
        when(authorRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(AuthorException.class, () -> authorService.getAuthorById(id));
    }
}
