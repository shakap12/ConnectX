package com.example.SocialMedia.Service;

import com.example.SocialMedia.Entity.Post;
import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Repository.PostRepository;
import com.example.SocialMedia.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @Override
    public Post createPost(Post post, String jwt) throws Exception {
        User reqUser=userService.getUserFromToken(jwt);
        User user= userService.getUserById(reqUser.getUserId());
        post.setPostedDate(LocalDate.now());
        post.setUser(user);
        return postRepository.save(post);
    }

    @Override
    public String deletePost(Long postId,String jwt) throws Exception {
            User reqUser=userService.getUserFromToken(jwt);
            Post post=findPostById(postId);
            User user=userService.getUserById(reqUser.getUserId());

            if(post.getUser().getUserId()!=user.getUserId()){
                throw new Exception("you cannot delete another users post");
            }
            postRepository.deleteById(postId);
            return "Post Deleted successfully";
    }

    @Override
    public List<Post> findPostByUserId(Long userId) {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(Long postId) {
        Post opt=postRepository.findById(postId)
                .orElseThrow(()-> new RuntimeException("Post with ID-"+postId+" NOT FOUND"));
        return opt;
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Long postId, String jwt) throws Exception {
        User reqUser=userService.getUserFromToken(jwt);
        Post post=findPostById(postId);
        User user=userService.getUserById(reqUser.getUserId());

        if(user.getSavedPosts().contains(post)){
            user.getSavedPosts().remove(post);
        }
        else user.getSavedPosts().add(post);
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likedPost(Long postId, String jwt) throws Exception {
        User reqUser=userService.getUserFromToken(jwt);
        Post post=findPostById(postId);
        User user=userService.getUserById(reqUser.getUserId());
        if(post.getLikedPost().contains(user)){
            post.getLikedPost().remove(user);
        }
        else post.getLikedPost().add(user);
        return postRepository.save(post);
    }
}

