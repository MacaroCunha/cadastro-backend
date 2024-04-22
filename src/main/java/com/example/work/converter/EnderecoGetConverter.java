package com.example.work.converter;

import com.example.work.dto.request.EnderecoRequest;
import com.example.work.model.EnderecoModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class EnderecoGetConverter implements Converter<EnderecoModel, EnderecoRequest> {
    @Override
    public EnderecoRequest convert(EnderecoModel source) {
        return EnderecoRequest.builder()
                .cep(source.getCep())
                .rua(source.getRua())
                .complemento(source.getComplemento())
                .numero(source.getNumero())
                .telefone(source.getTelefone())
                .build();
    }
}
