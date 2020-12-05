package com.tairovich.controller;

import com.tairovich.model.Message;
import com.tairovich.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/api/messenger/authors/{authorId}/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable long authorId, @PathVariable long messageId){
        return new ResponseEntity<>(messageService.getMessageById(authorId,messageId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Set<Message>> getMessages(@PathVariable long authorId){
        return new ResponseEntity<>(messageService.getMessages(authorId),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Message> addMessage(@PathVariable long authorId, @RequestBody Message message){
        return  new ResponseEntity<>(messageService.addMessage(authorId, message),HttpStatus.CREATED);
    }


    @PutMapping("/{messageId}")
    public ResponseEntity<Message> updateMessage(@PathVariable long authorId,
                                                 @PathVariable long messageId,
                                                 @RequestBody Message message){
        return new ResponseEntity<>(messageService.updateMessage(authorId,messageId, message), HttpStatus.CREATED);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<Message> deleteMessage(@PathVariable long authorId, @PathVariable long messageId){
        messageService.deleteMessage(authorId, messageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
