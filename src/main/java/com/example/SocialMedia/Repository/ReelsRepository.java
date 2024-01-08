package com.example.SocialMedia.Repository;

import com.example.SocialMedia.Entity.Reels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReelsRepository extends JpaRepository<Reels,Long> {

    public List<Reels> findByUserUserId(Long userId);
}
