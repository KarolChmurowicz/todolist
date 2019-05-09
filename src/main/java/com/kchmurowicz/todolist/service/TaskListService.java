package com.kchmurowicz.todolist.service;

import com.kchmurowicz.todolist.models.TaskList;
import com.kchmurowicz.todolist.repository.TaskListRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskListService {

    private final TaskListRepository taskListRepository;

    private final Logger LOGGER = LogManager.getLogger(this.getClass());


    public TaskListService(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    public TaskList save(TaskList taskList) {

        LOGGER.debug("creating a TaskList with name{}", taskList.getName());
        return taskListRepository.save(taskList);
    }

    public List<TaskList> findAll() {

        LOGGER.debug("Getting all TaskLists");
        return taskListRepository.findAll();
    }

    public void delete(Long taskListId) {

        LOGGER.debug("deleting a TaskList {}", taskListId);
        taskListRepository.deleteById(taskListId);
    }

    public TaskList update(TaskList taskList) {
        LOGGER.debug("updating a TaskList with name {}", taskList.getName());
        return taskListRepository.save(taskList);
    }


    public Optional<TaskList> findById(Long taskListId) {

        LOGGER.debug("finding a TaskList with id {}", taskListId);
        return taskListRepository.findById(taskListId);
    }
}
