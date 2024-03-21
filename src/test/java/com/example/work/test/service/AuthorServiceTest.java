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
    @Test
    public void getAuthorById_WhenAuthorExists_ReturnsAuthor() {
        long id = 1L;
        AuthorModel mockAuthor = new AuthorModel();
        mockAuthor.setId(id);
        when(authorRepository.findById(id)).thenReturn(Optional.of(mockAuthor));
        when(authorConverter.convertToDTO(mockAuthor)).thenReturn(new AuthorDto());
        var result = authorService.getAuthorById(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(authorRepository, times(1)).findById(id);
        verify(authorConverter, times(1)).convertToDTO(mockAuthor);
    }
    @Test
    public void getAuthorById_WhenAuthorDoesNotExist_ThrowsException() {
        long id = 1L;
        when(authorRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(AuthorException.class, () -> authorService.getAuthorById(id));
    }
}