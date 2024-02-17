package com.example.obra.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "obra")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_obra", nullable = false)
    private String nomeObra;

    @Column(name = "desc_obra", nullable = false)
    private String descObra;

    @Column(name = "data_pub")
    private Date dataPub;

    @Column(name = "data_expo")
    private Date dataExpo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private AutorModel autor;
}



