package org.example.newsWebsite.service.impl;

import org.example.newsWebsite.model.User;
import org.example.newsWebsite.service.api.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public User editUser(Long id, User user) {
        return null;
    }

    @Override
    public boolean deleteUser(String username) {
        return false;
    }

    @Override
    public boolean deleteUser(Long id) {
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public User getUser(Long id) {
        return null;
    }
}
