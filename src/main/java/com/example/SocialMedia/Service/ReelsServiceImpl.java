package com.example.SocialMedia.Service;

import com.example.SocialMedia.Entity.Reels;
import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Repository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelsServiceImpl implements ReelsService{

    @Autowired
    private ReelsRepository reelsRepository;

    @Autowired
    private UserService userService;
    @Override
    public Reels createReel(Reels reel, String jwt) {
        User reqUser=userService.getUserFromToken(jwt);
        Reels newReel= Reels.builder()
                .video(reel.getVideo())
                .title(reel.getTitle())
                .user(reqUser)
                .build();
        reelsRepository.save(newReel);
        return newReel;
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUsersReels(String jwt) {
        User reqUser=userService.findUserByJWT(jwt);
        return reelsRepository.findByUserUserId(reqUser.getUserId());
    }
}
