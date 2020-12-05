package com.tairovich.controller;

import com.tairovich.model.Author;
import com.tairovich.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/api/messenger/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/{authorId}")
    public ResponseEntity<Author> getAuthor(@PathVariable long authorId){
        return new ResponseEntity<>(authorService.getAuthor(authorId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Set<Author>> getAuthors(){
        return new ResponseEntity<>(authorService.getAuthors(),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Author> addAuthor(@RequestBody Author author){
        return new ResponseEntity<>(authorService.addAuthor(author),HttpStatus.CREATED);
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<Author> updateAuthor(@PathVariable long authorId, @RequestBody Author author){
        return new ResponseEntity<>(authorService.updateAuthor(authorId,author),HttpStatus.CREATED);
    }

    @DeleteMapping("/{profileName}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable String profileName){
      authorService.deleteAuthor(profileName);
      return new ResponseEntity<>(HttpStatus.OK);
    }
}
