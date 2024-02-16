package com.example.obra.controller;

import com.example.obra.dto.AutorDto;
import com.example.obra.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public ResponseEntity<Object> getAllAutores() {
        List<AutorDto> autoresDTO = autorService.getAllAutores();

        if (autoresDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum autor encontrado.");
        } else {
            return ResponseEntity.ok().body(autoresDTO);
        }
    }
}

