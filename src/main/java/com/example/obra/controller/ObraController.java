package com.example.obra.controller;

import com.example.obra.dto.request.ObraRequest;
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

    @PostMapping
    public ResponseEntity<ObraModel> createObra(@RequestBody ObraRequest obraRequest) {
        ObraModel createdObra = obraService.createObra(obraRequest);
        return ResponseEntity.ok(createdObra);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObraModel> getObraById(@PathVariable Long id) {
        return obraService.getObraById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObraModel> updateObra(@PathVariable Long id, @RequestBody ObraRequest obraRequest) {
        return ResponseEntity.ok(obraService.updateObra(id, obraRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObra(@PathVariable Long id) {
        obraService.deleteObra(id);
        return ResponseEntity.noContent().build();
    }
}
