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
public class WorkModel {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Long id;

        @Column(name = "workName", nullable = false)
        private String workName;

        @Column(name = "workDescription", nullable = false)
        private String workDescription;

        @Column(name = "publicationDate")
        @Temporal(TemporalType.TIMESTAMP)
        private Date publicationDate;

        @Column(name = "exhibitionDate")
        @Temporal(TemporalType.TIMESTAMP)
        private Date exhibitionDate;

}