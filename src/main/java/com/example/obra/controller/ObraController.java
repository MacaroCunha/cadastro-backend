package com.example.obra.controller;

import com.example.obra.model.ObraModel;
import com.example.obra.service.ObraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/obras")
@RequiredArgsConstructor
public class ObraController {

    private final ObraService obraService;

    @GetMapping
    public ResponseEntity<List<ObraModel>> getAllObras() {
        return ResponseEntity.ok(obraService.getAllObras());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObraModel> getObraById(@PathVariable Long id) {
        return obraService.getObraById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ObraModel> createObra(@RequestBody ObraModel obra) {
        return ResponseEntity.ok(obraService.createObra(obra));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObraModel> updateObra(@PathVariable Long id, @RequestBody ObraModel obra) {
        return ResponseEntity.ok(obraService.updateObra(id, obra));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObra(@PathVariable Long id) {
        obraService.deleteObra(id);
        return ResponseEntity.noContent().build();
    }
}






