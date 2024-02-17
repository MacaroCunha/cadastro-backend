package com.example.obra.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class ObraRequest {
    private String nomeObra;
    private String descObra;
    private Date dataPub;
    private Date dataExpo;
}


