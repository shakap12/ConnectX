package com.example.SocialMedia.Entity;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;
    @Column(name = "caption")
    private String caption;
    @Column(name = "imageURL")
    private String image;
    @Column(name = "videoURL")
    private String video;
    private LocalDate postedDate;

    @OneToMany
    private List<User> likedPost=new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany
    private List<Comment> comments=new ArrayList<>();
}
