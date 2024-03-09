package com.example.work.repository;

import com.example.work.model.AuthorWorkModel;
import com.example.work.model.WorkModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorWorkRepository extends JpaRepository<AuthorWorkModel, Long> {

    @Query("SELECT aw FROM AuthorWorkModel aw LEFT JOIN FETCH aw.work w WHERE aw.author.id = :authorId")
    List<AuthorWorkModel> findAuthorAndWorksById(@Param("authorId") Long authorId);
}











