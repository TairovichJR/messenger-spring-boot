package com.tairovich.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String message;
    private Date created;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "message")
    Set<Comment> comments = new HashSet<>();

    @ManyToOne
    @JsonBackReference //without this annotation it is doing infinite binding
    private Author author;

    public Message() {

    }

    public Message(String message) {
        this.message = message;
        this.created = new Date();
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }


    public Author getAuthor() {
        return author;
    }

    public void addComment(Comment comment) {
        comment.setMessage(this);
        comments.add(comment);
    }
}
