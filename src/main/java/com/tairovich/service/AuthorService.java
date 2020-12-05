package com.tairovich.service;

import com.tairovich.model.Author;

import java.util.Set;

public interface AuthorService {

    Author getAuthor(long id);

    Set<Author> getAuthors();

    Author addAuthor(Author author);

    Author updateAuthor(long id, Author author);

    void deleteAuthor(String profileName);
}
