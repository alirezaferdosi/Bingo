package org.example.newsWebsite.repository;

import org.example.newsWebsite.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepsitory extends JpaRepository<User, Long> {
}
