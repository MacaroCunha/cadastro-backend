package com.example.obra.service;

import com.example.obra.converter.AutorConverter;
import com.example.obra.dto.AutorDto;
import com.example.obra.dto.request.AutorRequest;
import com.example.obra.model.AutorModel;
import com.example.obra.model.ObraModel;
import com.example.obra.model.ObraAutorModel;
import com.example.obra.repository.AutorRepository;
import com.example.obra.repository.ObraRepository;
import com.example.obra.repository.ObraAutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;
    private final AutorConverter converter;

    @Autowired
    private ObraRepository obraRepository;

    @Autowired
    private ObraAutorRepository obraAutorRepository;

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
                    existingAutor.setCpf(autorDto.getCpf());
                    existingAutor.setDataNascimento(autorDto.getDataNascimento());
                    existingAutor.setEmail(autorDto.getEmail());
                    existingAutor.setPaisOrigem(autorDto.getPaisOrigem());
                    existingAutor.setSexo(autorDto.getSexo());
                    // Atualize outros campos conforme necessário
                    return convertToDTO(autorRepository.save(existingAutor));
                })
                .orElse(null);
    }

    public AutorDto createAutor(AutorRequest novoAutorDto) {
        AutorModel novoAutorModel = converter.convert(novoAutorDto);
        assert novoAutorModel != null;
        return convertToDTO(autorRepository.save(novoAutorModel));
    }

    public AutorDto associarObraAoAutor(Long idAutor, Long idObra) {
        AutorModel autor = autorRepository.findById(idAutor).orElse(null);
        ObraModel obra = obraRepository.findById(idObra).orElse(null);

        if (autor != null && obra != null) {
            ObraAutorModel obraAutor = new ObraAutorModel();
            obraAutor.setAutor(autor);
            obraAutor.setObra(obra);
            obraAutorRepository.save(obraAutor);

            return convertToDTO(autor);
        } else {
            // Trate o caso em que o autor ou obra não foi encontrado
            throw new IllegalArgumentException("Autor ou obra não encontrados");
        }
    }

    // Outros métodos relacionados às obras podem ser adicionados conforme necessário

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




