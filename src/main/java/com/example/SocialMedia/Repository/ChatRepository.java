package com.example.SocialMedia.Repository;

import com.example.SocialMedia.Entity.Chat;
import com.example.SocialMedia.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Long> {

    public List<Chat> findByUsersUserId(Long userId);

    @Query("select c from Chat c where :user Member of c.users And :reqUser Member of c.users")
    public Chat findChatByUsersId(@Param("user")User user,@Param("reqUser")User reqUser);
}
