package com.hrs.user.UserService.services;

import java.util.List;

import com.hrs.user.UserService.entities.User;

public interface UserService {

    // Create
    User savaUser(User user);

    // get all user
    List<User> getAllUser();

    // Get Single user
    User getUser(String userId);

    // Delete user
    String deleteUser(String userId);

    // Update user
    User updateUser(String userId, User newUser);
}
