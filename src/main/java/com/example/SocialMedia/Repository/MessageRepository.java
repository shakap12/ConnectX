package com.example.SocialMedia.Repository;

import com.example.SocialMedia.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

    public List<Message> findByChatId(Long chatId);
}
