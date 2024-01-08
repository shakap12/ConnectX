package com.example.SocialMedia.Controller;

import com.example.SocialMedia.Entity.Message;
import com.example.SocialMedia.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/create/{chatId}")
    public Message createMessage(@PathVariable("chatId")Long chatId,@RequestHeader("Authorization")String jwt, @RequestBody Message msg) throws Exception {
        return messageService.createMessage(chatId,jwt,msg);
    }

    @GetMapping("/messages/{chatId}")
    public List<Message> getChatMessages(@PathVariable("chatId")Long chatId){
        return messageService.getChatMessages(chatId);

    }
}
