package com.example.work.validations;

import com.example.work.dto.request.AuthorRequest;
import com.example.work.exception.AuthorException;
import com.example.work.exception.BusinessException;
import com.example.work.message.AuthorMessage;
import com.example.work.model.AuthorModel;
import com.example.work.repository.AuthorRepository;

import java.util.Optional;

public class AuthorServiceValidation {

    public static void validateNewAuthor(AuthorRequest newAuthorDto, AuthorRepository authorRepository) {
        Optional<AuthorModel> findCpf = authorRepository.existsByCpf(newAuthorDto.getCpf());
        Optional<AuthorModel> findEmail = authorRepository.existsByEmail(newAuthorDto.getEmail());

        if (findEmail.isPresent()) {
            throw new BusinessException(AuthorMessage.DUPLICATE_EMAIL, AuthorMessage.EMAIL_ALREADY_REGISTERED);
        }
        if (findCpf.isPresent()) {
            throw new BusinessException(AuthorMessage.DUPLICATE_CPF, AuthorMessage.CPF_ALREADY_REGISTERED);
        }
    }

    public static void validateExistingAuthor(Long id, AuthorModel existingAuthor) {
        if (existingAuthor == null) {
            throw new AuthorException(String.format(AuthorMessage.AUTHOR_NOT_FOUND, id));
        }
    }
}

