package com.example.SocialMedia.Controller;

import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping("/get")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users=userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long id){
        User user=userService.getUserById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user,@RequestHeader ("Authorization")String jwt){
        User updUser=userService.updateUser(user,jwt);
        return new ResponseEntity<>(updUser,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("User with id-"+id+" deleted!",HttpStatus.OK);
    }


    @GetMapping("/profile")
    public User getUserFromToken(@RequestHeader ("Authorization")String jwt){
        return userService.getUserFromToken(jwt);
    }

    @PutMapping("/follow/{userId1}/{userId2}")
    public User followUserHandler(@PathVariable("userId1")Long userId1,@PathVariable("userId2")Long userId2){
        User user=userService.followUserHandler(userId1,userId2);
        return user;
    }
}
