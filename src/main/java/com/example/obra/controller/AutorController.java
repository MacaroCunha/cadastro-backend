package com.example.obra.controller;

import com.example.obra.dto.AutorDto;
import com.example.obra.dto.AutorObraDto;
import com.example.obra.dto.request.AutorRequest;
import com.example.obra.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<Object> createAutor(@RequestBody AutorRequest novoAutorRequest) {
        try {
            AutorDto createdAutor = autorService.createAutor(novoAutorRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAutor);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode().value()).body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<AutorDto> updateAutor(@PathVariable Long id, @RequestBody AutorDto autorDto) {
        AutorDto updatedAutor = autorService.updateAutor(id, autorDto);

        return updatedAutor != null
                ? ResponseEntity.ok(updatedAutor)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/associar-obra")
    public ResponseEntity<Object> associarObraAoAutor(@RequestBody AutorObraDto autorObraDto) {
        try {
            // Lógica de associar obra ao autor
            return ResponseEntity.ok("Obra associada ao autor com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao associar obra ao autor: " + e.getMessage());
        }
    }
}
