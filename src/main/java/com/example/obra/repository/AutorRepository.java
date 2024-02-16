package com.example.obra.repository;

import com.example.obra.model.AutorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<AutorModel, Long> {

    AutorModel findByCpf(String cpf);

    AutorModel findByEmail(String email);

}


