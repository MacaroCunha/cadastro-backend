package com.example.obra.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "autor_obra")

public class ObraAutorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private AutorModel autor;

    @ManyToOne
    @JoinColumn(name = "obra_id", nullable = false)
    private ObraModel obra;

}
