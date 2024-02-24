package com.example.work.controller;

import com.example.work.dto.WorkDto;
import com.example.work.dto.error.ResponseError;
import com.example.work.dto.request.WorkRequest;
import com.example.work.exception.WorkException;
import com.example.work.message.WorkMessage;
import com.example.work.model.WorkModel;
import com.example.work.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/works")
@RequiredArgsConstructor
public class WorkController {

    private final WorkService workService;

    @GetMapping
    public ResponseEntity<List<WorkDto>> getAllWorks() {
        List<WorkDto> works = workService.getAllWorks();
        return works.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(works);
    }

    @PostMapping
    public ResponseEntity<Object> createWork(@RequestBody WorkRequest workRequest) {
        try {
            WorkDto createdWork = workService.createWork(workRequest);
            return ResponseEntity.ok(createdWork);
        } catch (WorkException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseError("", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseError("",WorkMessage.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkDto> getWorkById(@PathVariable Long id) {
        return workService.getWorkById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateWork(@PathVariable Long id, @RequestBody WorkRequest workRequest) {
        try {
            WorkDto updatedWork = workService.updateWork(id, workRequest);
            return ResponseEntity.ok(updatedWork);
        } catch (WorkException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(WorkMessage.UPDATED_WORK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWork(@PathVariable Long id) {
        workService.deleteWork(id);
        return ResponseEntity.noContent().build();
    }
}



