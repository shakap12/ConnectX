package com.example.SocialMedia.Service;

import com.example.SocialMedia.Entity.Comment;

public interface CommentService {

    public Comment createComment(Long postId,Long userId,Comment comment);

    public Comment likeComment(Long commentId,Long userId) throws Exception;

    public Comment findCommentById(Long commentId) throws Exception;
}
