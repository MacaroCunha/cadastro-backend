package com.example.obra.service;

import com.example.obra.dto.AutorDto;
import com.example.obra.model.AutorModel;
import com.example.obra.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<AutorDto> getAllAutores() {
        List<AutorDto> autoresDTO = autorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return autoresDTO;
    }

    private AutorDto convertToDTO(AutorModel autorModel) {
        AutorDto autorDTO = new AutorDto();
        autorDTO.setId(autorModel.getId());
        autorDTO.setNome(autorModel.getNome());
        return autorDTO;
    }
}






