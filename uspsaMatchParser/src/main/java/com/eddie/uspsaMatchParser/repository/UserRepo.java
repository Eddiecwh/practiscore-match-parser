package com.eddie.uspsaMatchParser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eddie.uspsaMatchParser.models.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
