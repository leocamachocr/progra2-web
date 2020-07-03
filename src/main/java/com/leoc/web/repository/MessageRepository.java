package com.leoc.web.repository;

import com.leoc.web.domain.Message;

import java.util.List;
import java.util.Vector;

public class MessageRepository {

    private List<Message> messages;

    private static MessageRepository instance;

    private MessageRepository() {
        messages = new Vector<>();
    }

    public static MessageRepository getInstance() {
        if (instance == null) instance = new MessageRepository();
        return instance;
    }


    public synchronized void add(Message message) {
        messages.add(message);
    }

    public List<Message> all() {
        return messages;
    }
}
