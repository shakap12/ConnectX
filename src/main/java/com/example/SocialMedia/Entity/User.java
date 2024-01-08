package com.example.SocialMedia.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long userId;
    @Column(name = "First_Name")
    private String firstName;
    @Column(name = "Last_Name")
    private String lastName;
    @Column(name = "Email")
    private String email;
    @Column(name = "Password")
    private String password;
    private List<Long> followers = new ArrayList<>();
    private List<Long> following = new ArrayList<>();
    public List<Long> getFollowers(){
        return followers;
    }
    public void setFollowers(){
        followers=new ArrayList<>();
    }
    public List<Long> getFollowing(){
        return following;
    }
    public void setFollowing(){
        following=new ArrayList<>();
    }
    @ManyToMany
    @JsonIgnore
    private List<Post> savedPosts = new ArrayList<>();

}