package com.example.SocialMedia.Repository;

import com.example.SocialMedia.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

   @Query(value = "select * from Post where user_id=:userId",nativeQuery = true)
    List<Post> findPostByUserId(Long userId);
}

