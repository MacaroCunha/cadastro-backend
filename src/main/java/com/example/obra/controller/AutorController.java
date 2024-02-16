package com.example.obra.controller;

import com.example.obra.dto.AutorDto;
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
    public ResponseEntity<Object> getAllAutores() {
        List<AutorDto> autoresDTO = autorService.getAllAutores();
        return autoresDTO.isEmpty()
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum autor encontrado.")
                : ResponseEntity.ok().body(autoresDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAutorById(@PathVariable Long id) {
        AutorDto autorDto = autorService.getAutorById(id);
        return autorDto != null
                ? ResponseEntity.ok().body(autorDto)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Autor não encontrado.");
    }

    @PostMapping
    public ResponseEntity<Object> createAutor(@RequestBody AutorDto novoAutorDto) {
        try {
            AutorDto createdAutor = autorService.createAutor(novoAutorDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAutor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar autor: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAutor(@PathVariable Long id, @RequestBody AutorDto autorDto) {
        try {
            AutorDto updatedAutor = autorService.updateAutor(id, autorDto);
            String message = (updatedAutor != null)
                    ? "Autor atualizado com sucesso"
                    : "Nenhuma atualização realizada. Autor já está atualizado.";
            return ResponseEntity.ok().body(message);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar autor: " + e.getMessage());
        }
    }
}
