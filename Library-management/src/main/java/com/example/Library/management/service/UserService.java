package com.example.Library.management.service;

import com.example.Library.management.entity.User;

public interface UserService {

    User createUser(User user);
    User getUserByEmail(String email);
}
