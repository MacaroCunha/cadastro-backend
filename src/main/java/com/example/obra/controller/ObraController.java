package com.example.obra.controller;

import com.example.obra.dto.request.ObraRequest;
import com.example.obra.exception.ExceptionObra;
import com.example.obra.message.ObraMessage;
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
        List<ObraModel> obras = obraService.getAllObras();
        return obras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(obras);
    }

    @PostMapping
    public ResponseEntity<Object> createObra(@RequestBody ObraRequest obraRequest) {
        try {
            ObraModel createdObra = obraService.createObra(obraRequest);
            return ResponseEntity.ok(createdObra);
        } catch (ExceptionObra e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ObraMessage.CREATED_OBRA);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObraModel> getObraById(@PathVariable Long id) {
        return obraService.getObraById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateObra(@PathVariable Long id, @RequestBody ObraRequest obraRequest) {
        try {
            ObraModel updatedObra = obraService.updateObra(id, obraRequest);
            return ResponseEntity.ok(updatedObra);
        } catch (ExceptionObra e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ObraMessage.UPDATED_OBRA);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObra(@PathVariable Long id) {
        obraService.deleteObra(id);
        return ResponseEntity.noContent().build();
    }
}


