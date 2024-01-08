package com.example.SocialMedia.Service;

import com.example.SocialMedia.Entity.Comment;
import com.example.SocialMedia.Entity.Post;
import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Repository.CommentRepository;
import com.example.SocialMedia.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;


    @Override
    public Comment createComment(Long postId, Long userId, Comment comment) {
        User reqUser=userService.getUserById(userId);
        Post reqPost=postService.findPostById(postId);
        comment.setUser(reqUser);
        comment.setComment(comment.getComment());
        comment.setCreatedAt(LocalDateTime.now());
        Comment savedComment=commentRepository.save(comment);
        reqPost.getComments().add(savedComment);
        postRepository.save(reqPost);
        return comment;
    }

    @Override
    public Comment likeComment(Long commentId, Long userId) throws Exception {
        Comment comment=findCommentById(commentId);
        User user=userService.getUserById(userId);
        if(comment.getLiked().contains(user))comment.getLiked().remove(user);
        else comment.getLiked().add(user);

        return commentRepository.save(comment);
    }

    @Override
    public Comment findCommentById(Long commentId) throws Exception {
        Optional<Comment> opt=commentRepository.findById(commentId);

        if(opt.isEmpty()){
            throw new Exception("comment does not exists!");
        }
        return opt.get();
    }
}
