package com.example.Library.management.controller;

import com.example.Library.management.entity.User;
import com.example.Library.management.security.SecurityConstant;
import com.example.Library.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;


    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        userService.createUser(user);

        return   ResponseEntity.status(HttpStatus.CREATED).body( "User created successfully");

    }

    @PostMapping("/login")
    public  ResponseEntity<String> loginUser(@RequestHeader("email") String email,@RequestHeader("password") String password){

        try {
            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email,password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ResponseEntity.ok("Login Successful");
        }catch (Exception e){
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }


}
