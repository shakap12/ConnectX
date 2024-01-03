package com.example.SocialMedia.Service;

import com.example.SocialMedia.Entity.Post;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostService {

    Post createPost(Post post, Long id) throws Exception;

    String deletePost(Long postId, Long userId) throws Exception;


    List<Post> findPostByUserId(Long userId);

    Post findPostById(Long postId);

    List<Post> findAllPosts();

    Post savedPost(Long postId,Long userId) throws Exception;

    Post likedPost(Long PostId, Long userId) throws Exception;

}
