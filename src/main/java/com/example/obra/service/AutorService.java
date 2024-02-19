package com.example.obra.service;

import com.example.obra.converter.AutorConverter;
import com.example.obra.dto.AutorDto;
import com.example.obra.dto.error.ResponseError;
import com.example.obra.dto.request.AutorRequest;
import com.example.obra.exception.ExceptionAutor;
import com.example.obra.message.AutorMessage;
import com.example.obra.model.AutorModel;
import com.example.obra.repository.AutorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;
    private final AutorConverter converter;
    private final ObjectMapper objectMapper;

    public List<AutorDto> getAllAutores() {
        List<AutorDto> autoresDTO = autorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        if (autoresDTO.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, AutorMessage.AUTORES_NOT_FOUND);
        }

        return autoresDTO;
    }

    public AutorDto getAutorById(Long id) {
        return autorRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ExceptionAutor(String.format(AutorMessage.AUTOR_NOT_FOUND, id)));
    }

    public AutorDto updateAutor(Long id, AutorDto autorDto) {
        AutorModel existingAutor = autorRepository.findById(id)
                .orElseThrow(() -> new ExceptionAutor(String.format(AutorMessage.AUTOR_NOT_FOUND, id)));

        updateExistingAutor(existingAutor, autorDto);

        AutorModel updatedAutor = autorRepository.save(existingAutor);
        return convertToDTO(updatedAutor);
    }

    public AutorDto createAutor(AutorRequest novoAutorDto) {
        try {
            validateAutorRequest(novoAutorDto);
            checkDuplicateCpfAndEmail(novoAutorDto.getCpf(), novoAutorDto.getEmail());

            AutorModel novoAutorModel = converter.convert(novoAutorDto);
            return convertToDTO(autorRepository.save(novoAutorModel));
        } catch (ExceptionAutor e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, AutorMessage.INTERNAL_SERVER_ERROR, e);
        }
    }

    private void updateExistingAutor(AutorModel existingAutor, AutorDto autorDto) {
        existingAutor.setNome(autorDto.getNome());
        existingAutor.setCpf(autorDto.getCpf());
        existingAutor.setDataNascimento(autorDto.getDataNascimento());
        existingAutor.setEmail(autorDto.getEmail());
        existingAutor.setPaisOrigem(autorDto.getPaisOrigem());
        existingAutor.setSexo(autorDto.getSexo());
    }

    private void checkDuplicateCpfAndEmail(String cpf, String email) {
        checkAndThrowDuplicate(AutorMessage.DUPLICATE_CPF, autorRepository.existsByCpf(cpf), AutorMessage.CPF_ALREADY_REGISTERED);
        checkAndThrowDuplicate(AutorMessage.DUPLICATE_EMAIL, autorRepository.existsByEmail(email), AutorMessage.EMAIL_ALREADY_REGISTERED);
    }

    private void checkAndThrowDuplicate(String campo, boolean isDuplicate, String errorMessage) {
        if (isDuplicate) {
            throw new ExceptionAutor(convertResponseErrorToJson(new ResponseError(campo, errorMessage)));
        }
    }

    private String convertResponseErrorToJson(ResponseError responseError) {
        try {
            return objectMapper.writeValueAsString(responseError);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, AutorMessage.INTERNAL_SERVER_ERROR, e);
        }
    }

    private void validateAutorRequest(AutorRequest autorRequest) {
        // Adicionar lógica de validação
    }

    private AutorDto convertToDTO(AutorModel autorModel) {
        return AutorDto.builder()
                .id(autorModel.getId())
                .cpf(autorModel.getCpf())
                .email(autorModel.getEmail())
                .nome(autorModel.getNome())
                .paisOrigem(autorModel.getPaisOrigem())
                .sexo(autorModel.getSexo())
                .dataNascimento(autorModel.getDataNascimento())
                .build();
    }
}






