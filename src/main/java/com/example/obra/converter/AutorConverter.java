package com.example.obra.converter;

import com.example.obra.dto.request.AutorRequest;
import com.example.obra.model.AutorModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AutorConverter implements Converter<AutorRequest, AutorModel> {

    @Override
    public AutorModel convert(AutorRequest autorDto) {
            return AutorModel.builder()
                    .cpf(autorDto.getCpf())
                    .dataNascimento(autorDto.getDataNascimento())
                    .email(autorDto.getEmail())
                    .nome(autorDto.getNome())
                    .paisOrigem(autorDto.getPaisOrigem())
                    .sexo(autorDto.getSexo())
                    .build();
    }
}
