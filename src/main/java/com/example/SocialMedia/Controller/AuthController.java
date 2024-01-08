package com.example.SocialMedia.Controller;

import com.example.SocialMedia.Configuration.JWTProvider;
import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Request.LoginRequest;
import com.example.SocialMedia.Response.AuthResponse;
import com.example.SocialMedia.Service.CustomUserDetailService;
import com.example.SocialMedia.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUser(@RequestBody User user) throws Exception {
        AuthResponse res=userService.createUser(user);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest){
        Authentication authentication=authenticate(loginRequest.getEmail(),loginRequest.getPassword());
        String token= JWTProvider.generateToken(authentication);
        AuthResponse res=new AuthResponse(token,"Login Success");
        return res;
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails=customUserDetailService.loadUserByUsername(email);
        if(userDetails==null)throw new BadCredentialsException("invalid unsername");
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("password wrong");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }


}
