package com.example.SocialMedia.Service;

import com.example.SocialMedia.Entity.Post;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostService {

    Post createPost(Post post, String jwt) throws Exception;

    String deletePost(Long postId,String jwt) throws Exception;


    List<Post> findPostByUserId(Long userId);

    Post findPostById(Long postId);

    List<Post> findAllPosts();

    Post savedPost(Long postId,String jwt) throws Exception;

    Post likedPost(Long PostId, String jwt) throws Exception;

}
