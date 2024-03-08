package com.example.work.model.connectModel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ConnectAuthorWorkId implements Serializable {

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "work_id")
    private Long workId;
}

