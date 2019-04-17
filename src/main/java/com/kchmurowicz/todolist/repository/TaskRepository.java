package com.kchmurowicz.todolist.repository;

import com.kchmurowicz.todolist.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long>{
}
