package com.example.news.repositories.user;

import com.example.news.entities.User;

import java.util.List;

public interface UserRepository {
    public User findUser(String username);
    List<User> allUsers();
    void deleteUser(Integer id);
    public User editUser(User user);

    public User addUser(User user);
}
