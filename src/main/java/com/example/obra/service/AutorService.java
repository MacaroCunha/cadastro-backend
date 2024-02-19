package com.example.obra.service;

import com.example.obra.converter.AutorConverter;
import com.example.obra.dto.AutorDto;
import com.example.obra.dto.ResponseError;
import com.example.obra.dto.request.AutorRequest;
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
        return autorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AutorDto getAutorById(Long id) {
        return autorRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public AutorDto updateAutor(Long id, AutorDto autorDto) {
        AutorModel existingAutor = autorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado com o ID: " + id));

        updateExistingAutor(existingAutor, autorDto);

        AutorModel updatedAutor = autorRepository.save(existingAutor);
        return convertToDTO(updatedAutor);
    }

    private void updateExistingAutor(AutorModel existingAutor, AutorDto autorDto) {
        existingAutor.setNome(autorDto.getNome());
        existingAutor.setCpf(autorDto.getCpf());
        existingAutor.setDataNascimento(autorDto.getDataNascimento());
        existingAutor.setEmail(autorDto.getEmail());
        existingAutor.setPaisOrigem(autorDto.getPaisOrigem());
        existingAutor.setSexo(autorDto.getSexo());
    }

    public AutorDto createAutor(AutorRequest novoAutorDto) {
        try {
            validateAutorRequest(novoAutorDto);
            checkDuplicateCpfAndEmail(novoAutorDto.getCpf(), novoAutorDto.getEmail());

            AutorModel novoAutorModel = converter.convert(novoAutorDto);
            return convertToDTO(autorRepository.save(novoAutorModel));
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            ResponseError responseError = new ResponseError("internal_error", "Erro ao criar autor");
            String jsonResponse = convertResponseErrorToJson(responseError);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, jsonResponse, e);
        }
    }

    private String convertResponseErrorToJson(ResponseError responseError) {
        try {
            return objectMapper.writeValueAsString(responseError);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao converter ResponseError para JSON", e);
        }
    }

    private void checkDuplicateCpfAndEmail(String cpf, String email) {
        if (autorRepository.existsByCpf(cpf)) {
            throw createDuplicateError("cpf", "CPF já cadastrado");
        }

        if (autorRepository.existsByEmail(email)) {
            throw createDuplicateError("email", "E-mail já cadastrado");
        }
    }

    private ResponseStatusException createDuplicateError(String campo, String message) {
        ResponseError responseError = new ResponseError(campo, message);
        String jsonResponse = convertResponseErrorToJson(responseError);
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, jsonResponse);
    }

    private void validateAutorRequest(AutorRequest autorRequest) {
        // Implement validation logic for AutorRequest (e.g., unique email, valid CPF, etc.)
        // Throw ResponseStatusException with HttpStatus.CONFLICT if validation fails
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



