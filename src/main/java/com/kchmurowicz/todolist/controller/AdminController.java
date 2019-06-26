package com.kchmurowicz.todolist.controller;

import com.kchmurowicz.todolist.models.Task;
import com.kchmurowicz.todolist.models.TaskList;
import com.kchmurowicz.todolist.service.TaskListService;
import com.kchmurowicz.todolist.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private TaskListService taskListService;
    private TaskService taskService;

    public AdminController(TaskListService taskListService, TaskService taskService) {
        this.taskListService = taskListService;
        this.taskService = taskService;
    }

    @GetMapping(value = "/lists")
    public @ResponseBody
    List<TaskList> getAllTasksLists() {
        LOGGER.debug("Received an admin request to get all TaskList");
        return taskListService.findAll();
    }

    @GetMapping(value = "/tasks")
    public @ResponseBody
    List<Task> getAllTasks() {
        LOGGER.debug("Received an admin request to get all Task");
        return taskService.findAll();
    }


}
