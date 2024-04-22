package com.example.work.service;

import com.example.work.converter.EnderecoGetConverter;
import com.example.work.converter.EnderecoPostConverter;
import com.example.work.dto.request.EnderecoRequest;
import com.example.work.exception.BusinessException;
import com.example.work.model.EnderecoModel;
import com.example.work.repository.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EnderecoService {

    private EnderecoRepository repository;
    private EnderecoPostConverter converter;
    private EnderecoGetConverter getConverter;

    public String post(EnderecoRequest r) {
        EnderecoModel converted = converter.convert(r);
        assert converted != null;
        repository.save(converted);
        return "ok";
    }

    public EnderecoRequest get(String telefone, String cep){
        EnderecoModel buscarEndereco = repository.buscarEndereco(telefone, cep);
        if(buscarEndereco == null){
            throw new BusinessException("cep não encontrado");
        }

         return getConverter.convert(buscarEndereco);
    }
}

