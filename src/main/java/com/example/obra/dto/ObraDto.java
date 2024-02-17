package com.example.obra.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ObraDto {

    private Long id;
    private String nomeObra;
    private String descObra;
    private Date dataPub;
    private Date dataExpo;

    public String getDescricaoCompleta() {
        return nomeObra + ": " + descObra;
    }
}

