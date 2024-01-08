package com.example.SocialMedia.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String chat_name;
    private String chat_image;

    @ManyToMany
    private List<User> users=new ArrayList<>();

    private LocalDateTime timestamp;
    @OneToMany(mappedBy = "chat")
    private List<Message> messages=new ArrayList<>();

}
