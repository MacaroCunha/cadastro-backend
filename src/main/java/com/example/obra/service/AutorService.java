package com.example.obra.service;

import com.example.obra.converter.AutorConverter;
import com.example.obra.dto.AutorDto;
import com.example.obra.dto.ObraDto;
import com.example.obra.dto.request.AutorRequest;
import com.example.obra.model.AutorModel;
import com.example.obra.model.ObraModel;
import com.example.obra.repository.AutorRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Builder
public class AutorService {

    private final AutorRepository autorRepository;

    private final AutorConverter converter;

    public List<AutorDto> getAllAutores() {
        return autorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AutorDto getAutorById(Integer id) {
        return autorRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public AutorDto updateAutor(Integer id, AutorDto autorDto) {
        return autorRepository.findById(id)
                .map(existingAutor -> {
                    existingAutor.setNome(autorDto.getNome());
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

    private AutorDto convertToDTO(AutorModel autorModel) {
        List<ObraDto> obrasDto = autorModel.getObras().stream()
                .map(this::convertObraToDTO)
                .collect(Collectors.toList());

        return AutorDto.builder()
                .id(autorModel.getId())
                .cpf(autorModel.getCpf())
                .email(autorModel.getEmail())
                .nome(autorModel.getNome())
                .paisOrigem(autorModel.getPaisOrigem())
                .sexo(autorModel.getSexo())
                .dataNascimento(autorModel.getDataNascimento())
                .obras(obrasDto)
                .build();
    }

    private ObraDto convertObraToDTO(ObraModel obraModel) {
        return ObraDto.builder()
                .id(obraModel.getId())
                .nomeObra(obraModel.getNomeObra())
                .descObra(obraModel.getDescObra())
                .dataPub(obraModel.getDataPub())
                .dataExpo(obraModel.getDataExpo())
                .build();
    }

    private ObraModel convertObraToModel(ObraDto obraDto) {
        ObraModel obraModel = new ObraModel();
        obraModel.setNomeObra(obraDto.getNomeObra());
        obraModel.setDescObra(obraDto.getDescObra());
        obraModel.setDataPub(obraDto.getDataPub());
        obraModel.setDataExpo(obraDto.getDataExpo());
        // Adicione outros campos conforme necessário
        return obraModel;
    }
}


