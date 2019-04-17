package com.kchmurowicz.todolist.repository;

import com.kchmurowicz.todolist.models.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskListRepository extends JpaRepository<TaskList,Long> {
}
