package com.example.SocialMedia.Service;

import com.example.SocialMedia.Entity.Chat;
import com.example.SocialMedia.Entity.Message;
import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Repository.ChatRepository;
import com.example.SocialMedia.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Message createMessage(Long chatId, String jwt, Message msg) throws Exception {
        User user=userService.findUserByJWT(jwt);
        Chat chat=chatService.findChatById(chatId);
        Message message= Message.builder()
                .chat(chat)
                .content(msg.getContent())
                .image(msg.getImage())
                .user(user)
                .timestamp(LocalDateTime.now())
                .build();

        Message newMsg=messageRepository.save(message);
        chat.getMessages().add(newMsg);
        chatRepository.save(chat);
        return message;
    }

    @Override
    public List<Message> getChatMessages(Long chatId) {
        return messageRepository.findByChatId(chatId);
    }
}
