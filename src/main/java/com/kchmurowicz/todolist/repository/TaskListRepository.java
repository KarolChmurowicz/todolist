package com.kchmurowicz.todolist.repository;

import com.kchmurowicz.todolist.models.TaskList;
import com.kchmurowicz.todolist.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskListRepository extends JpaRepository<TaskList,Long> {

    List<TaskList> findByUser(User user);

}
