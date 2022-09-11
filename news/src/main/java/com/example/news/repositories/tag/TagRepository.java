package com.example.news.repositories.tag;

import com.example.news.entities.User;

public interface TagRepository {
    public User findUser(String username);
}
