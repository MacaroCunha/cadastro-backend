package com.example.work.controller;

import com.example.work.dto.request.EnderecoRequest;
import com.example.work.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class EnderecoController {
    @Autowired
    private EnderecoService service;

    @PostMapping("/cadastrar")
    public String post(@RequestBody EnderecoRequest r) {
        return new ResponseEntity<>(this.service.post(r), HttpStatus.OK).getBody();
    }

    @GetMapping("/cep-cadastrado")
    public EnderecoRequest get(
            @Param("telefone") String telefone,
            @Param("cep") String cep) {
        return new ResponseEntity<>(this.service.get(telefone, cep), HttpStatus.OK).getBody();
    }
}