package com.example.SocialMedia.Service;

import com.example.SocialMedia.Entity.Chat;
import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Request.ChatRequest;

import java.util.List;

public interface ChatService {

    public Chat createChat(String jwt, ChatRequest chatRequest);

    public Chat findChatById(Long chatId) throws Exception;

    public List<Chat> findUserChat(String jwt);
}
