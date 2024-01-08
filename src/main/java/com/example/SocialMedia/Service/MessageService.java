package com.example.SocialMedia.Service;

import com.example.SocialMedia.Entity.Chat;
import com.example.SocialMedia.Entity.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageService {

    public Message createMessage(Long chatId, String jwt, Message msg) throws Exception;

    public List<Message> getChatMessages(Long chatId);


}
