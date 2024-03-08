package com.example.work.model;

import com.example.work.model.connectModel.ConnectAuthorWorkId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "author_work_connection")
public class AuthorWorkModel {

    @EmbeddedId
    private ConnectAuthorWorkId id;

    @ManyToOne
    @MapsId("author_Id")
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private AuthorModel author;

    @ManyToOne
    @MapsId("work_Id")
    @JoinColumn(name = "work_id", referencedColumnName = "id")
    private WorkModel work;
}




