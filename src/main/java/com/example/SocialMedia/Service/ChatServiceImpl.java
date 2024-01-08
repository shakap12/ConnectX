package com.example.SocialMedia.Service;

import com.example.SocialMedia.Entity.Chat;
import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Repository.ChatRepository;
import com.example.SocialMedia.Request.ChatRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserService userService;

    @Override
    public Chat createChat(String jwt, ChatRequest chatRequest) {
        User reqUser=userService.getUserFromToken(jwt);
        User user2=userService.getUserById(chatRequest.getUserId());
        Chat existsChat=chatRepository.findChatByUsersId(user2,reqUser);
        if(existsChat!=null){
            return existsChat;
        }
        Chat newChat=new Chat();
        newChat.getUsers().add(user2);
        newChat.getUsers().add(reqUser);
        newChat.setTimestamp(LocalDateTime.now());
        return chatRepository.save(newChat);
    }

    @Override
    public Chat findChatById(Long chatId) throws Exception {
        Optional<Chat> opt=chatRepository.findById(chatId);
        if(opt.isEmpty())throw new Exception("chat not found with ID-"+chatId);
        return opt.get();
    }

    @Override
    public List<Chat> findUserChat(String jwt) {
        User reqUser=userService.getUserFromToken(jwt);
        return chatRepository.findByUsersUserId(reqUser.getUserId());
    }
}
