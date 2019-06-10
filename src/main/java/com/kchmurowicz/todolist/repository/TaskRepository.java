package com.kchmurowicz.todolist.repository;

import com.kchmurowicz.todolist.models.Task;
import com.kchmurowicz.todolist.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long>{
    List<Task> findByUser(User user);
}
