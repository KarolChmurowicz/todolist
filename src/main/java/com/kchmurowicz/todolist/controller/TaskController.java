package com.kchmurowicz.todolist.controller;

import com.kchmurowicz.todolist.dto.TaskDto;
import com.kchmurowicz.todolist.models.Task;
import com.kchmurowicz.todolist.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public @ResponseBody
    Task createTask(@Valid @RequestBody TaskDto task, Principal principal) {
        LOGGER.debug("Received request to create a task with name {}", task.getName());
        Task createdTask = taskService.createTask(task, principal);
        LOGGER.debug("Returning create task with name {}", createdTask.getName());
        return createdTask;
    }

    @DeleteMapping(value = "/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        LOGGER.debug("Received request to delete a task with id {}", taskId);
        taskService.deleteTask(taskId);
    }

    @GetMapping
    public @ResponseBody
    List<Task> getTasks() {
        LOGGER.debug("Received request to get all tasks");
        return taskService.findAll();
    }

    @PutMapping(value = "/{taskId}")
    public @ResponseBody
    Task updateTask(@Valid @RequestBody TaskDto task, @PathVariable Long taskId) {
        LOGGER.debug("Received a request to update a task with name{} and id{}", task.getName(), taskId);
        Task updatedTask = taskService.updateTask(task, taskId);
        LOGGER.debug("Returning updated task with name{} and id{}", updatedTask.getName(), taskId);
        return updatedTask;
    }
}
