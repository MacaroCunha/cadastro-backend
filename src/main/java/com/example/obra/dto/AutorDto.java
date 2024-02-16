package com.example.obra.dto;

public class AutorDto {

    private Long id;
    private String nome;

    // Construtores, getters e setters

    public AutorDto() {
        // Construtor padrão
    }

    public AutorDto(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}



