package com.example.SocialMedia.Controller;

import com.example.SocialMedia.Entity.Chat;
import com.example.SocialMedia.Request.ChatRequest;
import com.example.SocialMedia.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/create")
    public Chat createChat(@RequestBody ChatRequest chatRequest,@RequestHeader("Authorization")String jwt){
        Chat chat=chatService.createChat(jwt,chatRequest);
        return chat;
    }
    @GetMapping("/user")
    private List<Chat> findUsersChat(@RequestHeader("Authorization")String jwt){
        List<Chat> chats=chatService.findUserChat(jwt);
        return chats;
    }


}
