package com.example.work.repository;

import com.example.work.model.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Integer> {
    @Query(value =
            "select * from tbcad_endereco AS fo" +
            " where fo.telefone = :telefone" +
            " and fo.cep = :cep", nativeQuery = true )
    EnderecoModel buscarEndereco(
            @Param("telefone") String telefone,
            @Param("cep") String cep);
}