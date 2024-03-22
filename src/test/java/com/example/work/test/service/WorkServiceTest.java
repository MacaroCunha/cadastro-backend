package com.example.work.test.service;

import com.example.work.dto.request.AuthorRequest;
import com.example.work.exception.BusinessException;
import com.example.work.model.AuthorModel;
import com.example.work.service.AuthorService;
import com.example.work.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class WorkServiceTest {

    @Mock
    private AuthorRepository authorRepository;
    @InjectMocks
    private AuthorService authorService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void createAuthor_WithDuplicateEmail_ShouldThrowBusinessException() {
        AuthorRequest authorRequest = new AuthorRequest();
        authorRequest.setEmail("existing@example.com");

        when(authorRepository.existsByEmail(authorRequest.getEmail())).thenReturn(Optional.of(new AuthorModel()));

        assertThrows(BusinessException.class, () -> authorService.createAuthor(authorRequest));
    }
    @Test
    void createAuthor_WithDuplicateCpf_ShouldThrowBusinessException() {
        AuthorRequest authorRequest = new AuthorRequest();
        authorRequest.setCpf("existingCpf");

        when(authorRepository.existsByCpf(authorRequest.getCpf())).thenReturn(Optional.of(new AuthorModel()));

        assertThrows(BusinessException.class, () -> authorService.createAuthor(authorRequest));
    }
}










































































































