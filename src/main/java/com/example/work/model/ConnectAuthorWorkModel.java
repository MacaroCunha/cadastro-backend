package com.example.work.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "author_work_connection")
public class ConnectAuthorWorkModel {

    @Id
    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorModel author;

    @Id
    @ManyToOne
    @JoinColumn(name = "work_id")
    private WorkModel work;
}






