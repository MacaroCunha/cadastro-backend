package com.example.work.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "author_work")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AuthorWorkModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private AuthorModel author;

    @ManyToOne
    @JoinColumn(name = "work_id", referencedColumnName = "id")
    private WorkModel work;
}