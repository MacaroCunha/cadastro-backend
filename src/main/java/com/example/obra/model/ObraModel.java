package com.example.obra.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
@Entity
@Table(name = "obra")
@Data
@NoArgsConstructor
public class ObraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_obra", nullable = false)
    private String nomeObra;

    @Column(name = "desc_obra", nullable = false)
    private String descObra;

    @Column(name = "data_pub", nullable = false)
    private Date dataPub;

    @Column(name = "data_expo", nullable = false)
    private Date dataExpo;
    public String getDescricaoCompleta() {
        return nomeObra + ": " + descObra;
    }
}


