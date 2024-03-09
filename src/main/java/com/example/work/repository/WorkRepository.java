package com.example.work.repository;

import com.example.work.model.WorkModel;
import com.example.work.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkRepository extends JpaRepository<WorkModel, Long> {}


