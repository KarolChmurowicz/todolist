package com.kchmurowicz.todolist.service;

import com.kchmurowicz.todolist.models.TaskList;
import com.kchmurowicz.todolist.repository.TaskListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListService(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    public TaskList save(TaskList taskList) {
        return taskListRepository.save(taskList);
    }

    public List<TaskList> findAll() {
        return taskListRepository.findAll();
    }

    public void delete(Long taskListId){
        taskListRepository.deleteById(taskListId);
    }
}
