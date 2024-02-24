package com.example.work.repository;

import com.example.work.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, Long> {

    @Query(value = "SELECT * FROM author WHERE author.cpf = :CPF", nativeQuery = true)
    Optional<AuthorModel> existsByCpf(@Param("CPF") String CPF);
    @Query(value = "SELECT * FROM author WHERE author.email = :EMAIL", nativeQuery = true)
    Optional<AuthorModel> existsByEmail(@Param("EMAIL") String EMAIL);
}







