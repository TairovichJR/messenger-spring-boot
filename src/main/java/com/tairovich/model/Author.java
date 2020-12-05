package com.tairovich.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;

    @Column(nullable = false)
    private String profileName;
    private Date created;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "author")
    @JsonManagedReference //without this annotation, it is doing infinite binding
    private Set<Message> messages = new HashSet<>();

    public Author() {

    }

    public Author(String firstName, String lastName, String profileName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileName = profileName;
        this.created = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void addMessages(Message message){
        message.setAuthor(this);
        messages.add(message);
    }

    public Set<Message> getMessages() {
        return messages;
    }
}
