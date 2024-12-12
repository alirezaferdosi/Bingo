package org.example.newsWebsite.service.api;

import org.example.newsWebsite.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("User")
public interface UserService {
    User addUser(User user);

    User editUser(Long id, User user);

    boolean deleteUser(String username);

    boolean deleteUser(Long id);

    List<User> getAllUsers();

    User getUser(String username);

    User getUser(Long id);
}