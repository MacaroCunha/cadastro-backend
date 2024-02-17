package com.example.obra.repository;

import com.example.obra.model.ObraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraRepository extends JpaRepository<ObraModel, Long> {
}

