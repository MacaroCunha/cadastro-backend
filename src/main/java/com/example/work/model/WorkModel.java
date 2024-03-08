package com.example.work.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "work")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class WorkModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_obra", nullable = false)
    private String workName;

    @Column(name = "desc_obra", nullable = false)
    private String workDescription;

    @Column(name = "data_pub")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publicationDate;

    @Column(name = "data_expo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exhibitionDate;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private AuthorModel author;

    @OneToMany(mappedBy = "work", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AuthorWorkModel> workAuthors;
}


