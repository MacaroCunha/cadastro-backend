package com.example.obra.service;

import com.example.obra.dto.AutorDto;
import com.example.obra.model.AutorModel;
import com.example.obra.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;

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
        return autorRepository.findById(id)
                .map(existingAutor -> {
                    existingAutor.setNome(autorDto.getNome());
                    // Atualize outros campos conforme necessário
                    return convertToDTO(autorRepository.save(existingAutor));
                })
                .orElse(null);
    }

    public AutorDto createAutor(AutorDto novoAutorDto) {
        AutorModel novoAutorModel = convertToModel(novoAutorDto);
        return convertToDTO(autorRepository.save(novoAutorModel));
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

    private AutorModel convertToModel(AutorDto autorDto) {
        AutorModel autorModel = new AutorModel();
        autorModel.setNome(autorDto.getNome());
        // Adicione outros campos conforme necessário
        return autorModel;
    }
}

