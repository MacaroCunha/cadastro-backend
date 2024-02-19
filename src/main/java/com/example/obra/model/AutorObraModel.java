package com.example.obra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "autor_obra")

public class AutorObraModel {
    @Id
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private AutorModel autor;

    @Id
    @ManyToOne
    @JoinColumn(name = "obra_id")
    private ObraModel obra;
}





