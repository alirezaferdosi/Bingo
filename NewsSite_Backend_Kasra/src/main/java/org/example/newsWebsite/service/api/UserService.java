package org.example.newsWebsite.service.api;

import org.example.newsWebsite.model.User;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component("User")
public interface UserService {
    @Transactional
    User addUser(User user);

    @Transactional
    Long longIn(String username, String password);

    @Transactional
    User editUser(User user);

    @Transactional
    boolean deleteUser(String username);

    @Transactional
    boolean deleteUser(Long id);

    @Transactional
    List<User> getAllUsers();

    @Transactional
    User getUser(String username);

    @Transactional
    User getUser(Long id);

    @Transactional
    Boolean uploadProfileImage(MultipartFile file, Long id);

    @Transactional
    Resource downloadProfileImage(Long id);

    @Transactional
    boolean isUserExist(String username);

    @Transactional
    boolean isUserExist(Long id);

    @Transactional
    boolean isEmailExist(String email);

    @Transactional
    boolean isPhoneExist(String phone);

    @Transactional
    Integer getNumberOfUsers();

    @Transactional
    Boolean changeFavorites(Long id, Byte favorites);

    @Transactional
    Byte getFavorites(Long id);

    @Transactional
    boolean changeFavoritesCategory(Long userid, String category, boolean flag);
}