package com.example.work.converter;

import com.example.work.dto.response.AuthorDto;
import com.example.work.dto.request.AuthorRequest;
import com.example.work.model.AuthorModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter implements Converter<AuthorRequest, AuthorModel> {

    public AuthorModel convert(AuthorRequest authorRequest) {
        validateAuthorRequest(authorRequest);

        return AuthorModel.builder()
                .cpf(authorRequest.getCpf())
                .birthDate(authorRequest.getBirthDate())
                .email(authorRequest.getEmail())
                .name(authorRequest.getName())
                .countryOfOrigin(authorRequest.getCountryOfOrigin())
                .gender(authorRequest.getGender())
                .build();
    }
    private void validateAuthorRequest(AuthorRequest authorRequest) {
    }
    public AuthorDto convertToDTO(AuthorModel authorModel) {
        return AuthorDto.builder()
                .id(authorModel.getId())
                .cpf(authorModel.getCpf())
                .email(authorModel.getEmail())
                .name(authorModel.getName())
                .countryOfOrigin(authorModel.getCountryOfOrigin())
                .gender(authorModel.getGender())
                .birthDate(authorModel.getBirthDate())
                .build();
    }
}