package com.example.obra.controller;

import com.example.obra.dto.AutorDto;
import com.example.obra.dto.request.AutorRequest;
import com.example.obra.exception.ExceptionAutor;
import com.example.obra.message.AutorMessage;
import com.example.obra.model.AutorObraModel;
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
        return autoresDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(autoresDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDto> getAutorById(@PathVariable Long id) {
        try {
            AutorDto autorDto = autorService.getAutorById(id);
            return ResponseEntity.ok(autorDto);
        } catch (ExceptionAutor e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createAutor(@RequestBody AutorRequest novoAutorRequest) {
        try {
            AutorDto createdAutor = autorService.createAutor(novoAutorRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(AutorMessage.CREATED_AUTOR);
        } catch (ExceptionAutor e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, AutorMessage.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAutor(@PathVariable Long id, @RequestBody AutorDto autorDto) {
        try {
            AutorDto updatedAutor = autorService.updateAutor(id, autorDto);
            return ResponseEntity.ok(updatedAutor);
        } catch (ExceptionAutor e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/associar-obra")
    public ResponseEntity<Object> associarObraAoAutor(@RequestBody AutorObraModel autorObraDto) {
        try {
            // Lógica de associar obra ao autor
            return ResponseEntity.ok(AutorMessage.ASSOCIATED_OBRA_SUCCESS);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(AutorMessage.ASSOCIATE_OBRA_ERROR, e.getMessage()));
        }
    }
}
