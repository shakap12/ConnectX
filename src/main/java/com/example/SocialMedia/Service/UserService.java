package com.example.SocialMedia.Service;

import com.example.SocialMedia.Entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(User user, Long userId);

    void deleteUser(Long id);
}
