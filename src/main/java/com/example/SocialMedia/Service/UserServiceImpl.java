package com.example.SocialMedia.Service;

import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
    }

    @Override
    public User updateUser(User user, Long userId) {
        User exsUser=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        if(Objects.nonNull(user.getUserId()))exsUser.setUserId(user.getUserId());
        if(Objects.nonNull(user.getFirstName()))exsUser.setFirstName(user.getFirstName());
        if(Objects.nonNull(user.getLastName()))exsUser.setLastName(user.getLastName());
        if(Objects.nonNull(user.getEmail()))exsUser.setEmail(user.getEmail());
        if(Objects.nonNull(user.getPassword()))exsUser.setPassword(user.getPassword());
        userRepository.save(exsUser);
        return exsUser;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
