package com.example.obra.controller;

import com.example.obra.dto.AutorDto;
import com.example.obra.dto.request.AssociarObraAutorDTO;
import com.example.obra.dto.request.AutorRequest;
import com.example.obra.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public ResponseEntity<List<AutorDto>> getAllAutores() {
        List<AutorDto> autoresDTO = autorService.getAllAutores();
        return autoresDTO.isEmpty()
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(autoresDTO)
                : ResponseEntity.ok(autoresDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDto> getAutorById(@PathVariable Long id) {
        AutorDto autorDto = autorService.getAutorById(id);
        return autorDto != null
                ? ResponseEntity.ok(autorDto)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping
    public ResponseEntity<AutorDto> createAutor(@RequestBody AutorRequest novoAutorRequest) {
        AutorDto createdAutor = autorService.createAutor(novoAutorRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAutor);
    }

    @PutMapping("/associar-obra")
    public ResponseEntity<Object> associarObraAoAutor(@RequestBody AssociarObraAutorDTO dto) {
        try {
            AutorDto autorAssociado = autorService.associarObraAoAutor(dto.getIdAutor(), dto.getIdObra());
            return ResponseEntity.ok(autorAssociado);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao associar obra ao autor: " + e.getMessage());
        }
    }
}
