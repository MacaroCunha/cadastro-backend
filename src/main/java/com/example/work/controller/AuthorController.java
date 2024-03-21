package com.example.work.controller;

import com.example.work.dto.error.ResponseMessage;
import com.example.work.dto.request.AuthorRequest;
import com.example.work.dto.response.AuthorDto;
import com.example.work.dto.response.ListWorkAuthorDto;
import com.example.work.exception.AuthorException;
import com.example.work.message.AuthorMessage;
import com.example.work.model.AuthorWorkModel;
import com.example.work.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        List<AuthorDto> authorsDTO = authorService.getAllAuthors();
        return authorsDTO.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(authorsDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id) {
        try {
            AuthorDto authorDto = authorService.getAuthorById(id);
            if (authorDto != null) {
                return ResponseEntity.ok(authorDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (AuthorException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{authorId}/works")
    public ResponseEntity<ListWorkAuthorDto> getAuthorWorks(@PathVariable Long authorId) {
        try {
            ListWorkAuthorDto authorWorks = authorService.getAuthorWorks(authorId);
            return ResponseEntity.ok(authorWorks);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createAuthor(@RequestBody AuthorRequest newAuthorRequest) {
        authorService.createAuthor(newAuthorRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseMessage
                        .builder()
                        .message(AuthorMessage.CREATED_AUTOR)
                        .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        try {
            AuthorDto updatedAuthor = authorService.updateAuthor(id, authorDto);
            return ResponseEntity.ok(updatedAuthor);
        } catch (AuthorException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/connect-work")
    public ResponseEntity<Object> connectAuthorWork(@RequestBody AuthorWorkModel authorWorkModel) {
        try {
            return ResponseEntity.ok(AuthorMessage.ASSOCIATED_WORK_SUCCESS);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(AuthorMessage.ASSOCIATE_WORK_ERROR, e.getMessage()));
        }
    }
}
