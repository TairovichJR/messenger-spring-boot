package com.tairovich.service;

import com.tairovich.model.Message;
import java.util.Set;

public interface MessageService {

    Message getMessageById(long authorId, long messageId);

    Set<Message> getMessages(long authorId);

    Message addMessage(long authorId, Message message);

    Message updateMessage(long authorId, long messageId, Message message);

    void deleteMessage(long authorId, long messageId);

}
