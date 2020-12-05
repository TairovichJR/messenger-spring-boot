package com.tairovich.service.serviceImpl;

import com.tairovich.model.Author;
import com.tairovich.repository.AuthorRepository;
import com.tairovich.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author getAuthor(long id) {
        return authorRepository.findById(id).get();
    }

    @Override
    public Set<Author> getAuthors() {
        Set<Author> authors = new HashSet<>();
        authorRepository.findAll().forEach( i -> authors.add(i));
        return authors;
    }

    @Override
    public Author addAuthor(Author author) {

        Set<Author> existingAuthors = new HashSet<>();
        authorRepository.findAll().forEach(existingAuthors::add);

        Optional<Author> foundAuthor = existingAuthors
                .stream().filter( e -> e.getProfileName().equals(author.getProfileName())).findFirst();


        if (!foundAuthor.isPresent()){
            author.setCreated(new Date());
            return authorRepository.save(author);
        }
        System.out.println("Author with profile " + author.getProfileName() + " already exists");
        return null;
    }

    @Override
    public Author updateAuthor(long id,Author author) {
        Optional<Author> returnedAuthor = authorRepository.findById(id);
        if (returnedAuthor.isPresent()){
            Author updatedAuthor = returnedAuthor.get();
            updatedAuthor.setFirstName(author.getFirstName());
            updatedAuthor.setLastName(author.getLastName());
            updatedAuthor.setCreated(new Date());
            updatedAuthor.setProfileName(author.getProfileName());
            return authorRepository.save(updatedAuthor);
        }
        System.out.println("There was no author by id " + id + " returned from the server");
        return null;
    }

    @Override
    public void deleteAuthor(String profileName) {

        Set<Author> authors = new HashSet<>();
        authorRepository.findAll().forEach(authors::add);
        Author returnedAuthor = authors.stream().filter( auth -> auth.getProfileName().equals(profileName)).findFirst().orElse(null);

        if (returnedAuthor != null){
            authorRepository.deleteById(returnedAuthor.getId());
        }else{
            System.out.println("Cannot find author with profile name: " + profileName);
        }
    }
}
