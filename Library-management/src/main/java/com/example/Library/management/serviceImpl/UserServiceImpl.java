package com.example.Library.management.serviceImpl;

import com.example.Library.management.entity.User;
import com.example.Library.management.enums.Role;
import com.example.Library.management.repository.UserRepository;
import com.example.Library.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {

        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Email is already taken");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.STUDENT);


         return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {

        if(userRepository.findByEmail(email).isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"User Not found");
        }


        return userRepository.findByEmail(email).get();
    }
}
