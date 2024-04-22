package com.example.work.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbcad_endereco")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoModel {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Integer id;

        @Column(name = "created_at", nullable = false)
        private LocalDateTime dt_criacao;

        @Column(name = "update_at")
        private LocalDateTime dt_alteracao;

        @Column(name = "cep", nullable = false)
        private String cep;

        @Column(name = "rua", nullable = false)
        private String rua;

        @Column(name = "numero", nullable = false)
        private String numero;

        @Column(name = "complemento", nullable = false)
        private String complemento;

        @Column(name = "telefone", nullable = false)
        private String telefone;
}