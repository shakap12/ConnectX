package com.example.SocialMedia.Controller;


import com.example.SocialMedia.Entity.Reels;
import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Service.ReelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reels")
public class ReelsController {

    @Autowired
    private ReelsService reelsService;

    @PostMapping("/create")
    public Reels createReel(@RequestBody Reels reel, @RequestHeader("Authorization") String jwt){
        return reelsService.createReel(reel,jwt);
    }

    @GetMapping("/get")
    public List<Reels> getAllReels(){
        return reelsService.findAllReels();
    }

    @GetMapping("/user")
    public List<Reels> getReelsForUser(@RequestHeader("Authorization")String jwt){
        return reelsService.findUsersReels(jwt);
    }
}
