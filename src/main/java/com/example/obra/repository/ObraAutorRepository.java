package com.example.obra.repository;

import com.example.obra.model.ObraAutorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraAutorRepository extends JpaRepository<ObraAutorModel, Long> {

}
