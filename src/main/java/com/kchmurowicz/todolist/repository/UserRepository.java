package com.kchmurowicz.todolist.repository;

import com.kchmurowicz.todolist.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
User findByUsername(String username);
}
