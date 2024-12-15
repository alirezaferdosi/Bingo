package org.example.newsWebsite.service.impl;

import org.example.newsWebsite.model.User;
import org.example.newsWebsite.repository.UserRepository;
import org.example.newsWebsite.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User editUser(User user) {
        User u = userRepository.findById(user.getId()).get();
        if (Objects.nonNull(u)) {
            if(Objects.nonNull(user.getUsername()) && !user.getUsername().isEmpty()) {
                u.setUsername(user.getUsername());
            }
            if(Objects.nonNull(user.getPassword()) && !user.getPassword().isEmpty()) {
                u.setPassword(user.getPassword());
            }
            if(Objects.nonNull(user.getEmail()) && !user.getEmail().isEmpty()) {
                u.setEmail(user.getEmail());
            }
            if(Objects.nonNull(user.getPhone()) && !user.getPhone().isEmpty()) {
                u.setPhone(user.getPhone());
            }
            if (Objects.nonNull(user.getPhotoPath()) && !user.getPhotoPath().isEmpty()) {
                u.setPhotoPath(user.getPhotoPath());
            }

            return userRepository.save(u);
        }
        return null;
    }

    @Override
    public boolean deleteUser(String username) {
        if(isUserExist(username)) {
            userRepository.delete(this.getUser(username));
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(Long id) {
        if(isUserExist(id)) {
            userRepository.delete(this.getUser(id));
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String username) {
        for(User user : userRepository.findAll()) {
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    @Override
    public User getUser(Long id) {
        if(userRepository.existsById(id)){
            return userRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public boolean isUserExist(String username) {
        for(User user : userRepository.findAll()) {
            if(user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isUserExist(Long id) {
        return userRepository.existsById(id);
    }
}