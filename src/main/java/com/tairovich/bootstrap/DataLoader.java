package com.tairovich.bootstrap;

import com.tairovich.model.Author;
import com.tairovich.model.Comment;
import com.tairovich.model.Message;
import com.tairovich.repository.AuthorRepository;
import com.tairovich.repository.CommentRepository;
import com.tairovich.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void run(String... args) throws Exception {
        loadStartupData();
    }

    private void loadStartupData(){
        Author omon = new Author("Omonjon", "Yokubov","tairovich_jr");
        Author savedOmon = authorRepository.save(omon);

        Message newMessage = new Message("Hi there, this is the first message ever");
        newMessage.setAuthor(savedOmon);

        messageRepository.save(newMessage);

        omon.addMessages(newMessage);

        savedOmon = authorRepository.save(omon);

        Comment randyComment = new Comment("Randy",new Date(),"Good job man");
        Comment alexComment = new Comment("Alex",new Date(),"Excellent message");
        Comment helenComment = new Comment("Helen",new Date(),"Wow cool");

        Comment savedRandyComment = commentRepository.save(randyComment);
        Comment savedAlexComment = commentRepository.save(alexComment);
        Comment savedHelenComment = commentRepository.save(helenComment);

        newMessage.addComment(savedAlexComment);
        newMessage.addComment(savedRandyComment);
        newMessage.addComment(savedHelenComment);

        messageRepository.save(newMessage);

        Message badMessage = new Message("Bad message here");
        messageRepository.save(badMessage);

        Message newMessage2 = new Message("Hi there, this is from Omon");
        newMessage2.setAuthor(savedOmon);

        messageRepository.save(newMessage2);

        omon.addMessages(newMessage2);

        authorRepository.save(omon);

    }
}
