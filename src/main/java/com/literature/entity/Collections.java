package com.literature.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_collections")
public class Collections implements Serializable {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "book_id")
    private String bookId;
    @Column(name = "user_id")
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
