package com.example.SocialMedia.Service;

import com.example.SocialMedia.Entity.Reels;
import com.example.SocialMedia.Entity.User;

import java.util.List;

public interface ReelsService {

    public Reels createReel(Reels reel, String jwt);

    public List<Reels> findAllReels();

    public List<Reels> findUsersReels(String jwt);
}
