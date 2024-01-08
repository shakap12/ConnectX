package com.example.SocialMedia.Service;

import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Response.AuthResponse;

import java.util.List;

public interface UserService {
    AuthResponse createUser(User user) throws Exception;

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(User user, String jwt);

    void deleteUser(Long id);

    User getUserFromToken(String jwt);

    User findUserByJWT(String jwt);

    User followUserHandler(Long userId1, Long userId2);
}
