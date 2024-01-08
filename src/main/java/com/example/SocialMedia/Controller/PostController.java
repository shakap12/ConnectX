package com.example.SocialMedia.Controller;

import com.example.SocialMedia.Entity.Post;
import com.example.SocialMedia.Response.APIResponse;
import com.example.SocialMedia.Service.PostService;
import jakarta.persistence.PostRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user")
    private ResponseEntity<Post> createPost(@RequestBody Post post, @RequestHeader("Authorization")String jwt) throws Exception {
        Post newPost=postService.createPost(post,jwt);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{postId}")
    private ResponseEntity<APIResponse> deletePost(@PathVariable("postId") Long postid,@RequestHeader("Authorization")String jwt) throws Exception {
        String msg=postService.deletePost(postid,jwt);
        APIResponse resp=APIResponse.builder()
                .message(msg)
                .status(true)
                .build();

        return new ResponseEntity<>(resp,HttpStatus.OK);
    }

    @GetMapping("/get/{postId}")
    private ResponseEntity<Post> findPostById(@PathVariable("postId") Long id){
        Post post=postService.findPostById(id);
        return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
    }

    @GetMapping("/getuser/{userId}")
    private ResponseEntity<List<Post>> findUserPost(@PathVariable("userId") Long id){
        List<Post> postsForAUser=postService.findPostByUserId(id);
        return new ResponseEntity<>(postsForAUser,HttpStatus.OK);
    }

    @GetMapping("/get")
    private ResponseEntity<List<Post>> findAllPost(){
        List<Post> list=postService.findAllPosts();
        return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
    }

    @PutMapping("/saved/{postId}")
    private ResponseEntity<Post> savedPostHandler(@PathVariable("postId") Long postId,@RequestHeader("Authorization")String jwt) throws Exception {
        Post post=postService.savedPost(postId,jwt);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @PutMapping("/liked/{postId}")
    private ResponseEntity<Post> likedPostHandler(@PathVariable("postId")Long postId,@RequestHeader("Authorization")String jwt) throws Exception {
        Post post=postService.likedPost(postId,jwt);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

}
