package com.kchmurowicz.todolist.controller;

import com.kchmurowicz.todolist.dto.TaskDto;
import com.kchmurowicz.todolist.models.Task;
import com.kchmurowicz.todolist.service.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping()
    public @ResponseBody
    Task createTask(@Valid @RequestBody TaskDto task) {
        return taskService.createTask(task);
    }


}
