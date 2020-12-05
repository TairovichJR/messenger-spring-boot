package com.tairovich.service.serviceImpl;

import com.tairovich.model.Author;
import com.tairovich.model.Message;
import com.tairovich.repository.AuthorRepository;
import com.tairovich.repository.MessageRepository;
import com.tairovich.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Message getMessageById(long authorId, long messageId) {
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isPresent()){
            Author returnedAuthor = author.get();
            Message foundMessage = returnedAuthor.getMessages().stream().filter( m -> m.getId() == messageId).findFirst().orElse(null);
            if (foundMessage != null){
                return foundMessage;
            }
        }
        return  null;
    }

    @Override
    public Set<Message> getMessages(long authorId) {
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isPresent()){
            Author returnedAuthor = author.get();
            Set<Message> authorMessages = returnedAuthor.getMessages();
            return authorMessages;
        }
        return null;
    }

    @Override
    public Message addMessage(long authorId, Message message) {
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isPresent()){
            Author foundAuthor = author.get();
            message.setAuthor(foundAuthor);
            message.setCreated(new Date());
            Message savedMessage = messageRepository.save(message);
            foundAuthor.addMessages(savedMessage);
            return savedMessage;
        }
        return null;
    }

    @Override
    public Message updateMessage(long authorId, long messageId, Message message) {
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isPresent()){
            Author foundAuthor = author.get();
            Optional<Message> foundMessage = foundAuthor.getMessages().stream().filter(m -> m.getId() == messageId).findFirst();
            if (foundMessage.isPresent()){
                Message updatedMessage = foundMessage.get();
                updatedMessage.setCreated(new Date());
                updatedMessage.setMessage(message.getMessage());
                return messageRepository.save(updatedMessage);
            }
        }
        return null;
    }

    @Override
    public void deleteMessage(long authorId, long messageId) {

        Optional<Author> author = authorRepository.findById(authorId);

        if (author.isPresent()){
            Author foundAuthor = author.get();
            Optional<Message> foundMessage = foundAuthor.getMessages().stream().filter(m -> m.getId() == messageId).findFirst();
            if (foundMessage.isPresent()){
                messageRepository.deleteById(foundMessage.get().getId());
            }
        }
    }


}
