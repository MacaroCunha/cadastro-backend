package com.example.work.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "work")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_obra", nullable = false)
    private String workName;

    @Column(name = "desc_obra", nullable = false)
    private String workDescription;

    @Column(name = "data_pub")
    private Date publicationDate;

    @Column(name = "data_expo")
    private Date exhibitionDate;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private AuthorModel autor;
}




