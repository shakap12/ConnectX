package com.example.SocialMedia.Service;

import com.example.SocialMedia.Configuration.JWTProvider;
import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Repository.UserRepository;
import com.example.SocialMedia.Response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse createUser(User user) throws Exception {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setFollowers(new ArrayList<Long>());
        user.setFollowing(new ArrayList<Long>());
        if(userRepository.findByEmail(user.getEmail())!=null)throw new Exception("email already present");
        User savedUser=userRepository.save(user);

        Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
        String token= JWTProvider.generateToken(authentication);
        AuthResponse res=new AuthResponse(token,"Register Success");
        return res;
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
    public User updateUser(User user, String jwt) {
        Long userId=findUserByJWT(jwt).getUserId();
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

    public User getUserFromToken(String jwt){
//        System.out.println("jwt---->"+jwt);
//        String email=
        User user=findUserByJWT(jwt);
        return user;
    }

    public User findUserByJWT(String jwt){
        String email=JWTProvider.getEmailFromJWTToken(jwt);
        User user=userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User followUserHandler(Long userId1, Long userId2) {

        User user1=getUserById(userId1);
        User user2=getUserById(userId2);
        return user1;
    }
}
