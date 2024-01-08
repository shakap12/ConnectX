package com.example.SocialMedia.Controller;

import com.example.SocialMedia.Entity.Comment;
import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Service.CommentService;
import com.example.SocialMedia.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/post/{postId}")
    public Comment createComment(@RequestBody Comment comment, @RequestHeader("Authorization")String jwt,@PathVariable("postId")Long postId){
        User reqUser=userService.getUserFromToken(jwt);
        Comment createdComment=commentService.createComment(postId, reqUser.getUserId(), comment);
        return createdComment;
    }

    @PutMapping("/liked/{commentId}")
    public Comment likeComment(@RequestHeader("Authorization")String jwt,@PathVariable("commentId") Long comId) throws Exception {
        User reqUser=userService.getUserFromToken(jwt);
        Comment updatedComment=commentService.likeComment(comId,reqUser.getUserId());
        return updatedComment;
    }

    @GetMapping("/get/{commentId}")
    public Comment getComment(@PathVariable("commentId") Long id) throws Exception {
        return commentService.findCommentById(id);
    }
}
