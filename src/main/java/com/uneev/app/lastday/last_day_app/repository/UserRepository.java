package com.uneev.app.lastday.last_day_app.repository;

import com.uneev.app.lastday.last_day_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
