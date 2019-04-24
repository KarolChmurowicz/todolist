package com.kchmurowicz.todolist.controller;

import com.kchmurowicz.todolist.dto.TaskDto;
import com.kchmurowicz.todolist.models.Task;
import com.kchmurowicz.todolist.service.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @DeleteMapping(value = "/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }

    @GetMapping
    public @ResponseBody
    List<Task> getTasks() {
        return taskService.findAll();
    }
    @PutMapping
    public @ResponseBody Task updateTask(@Valid @RequestBody TaskDto task){
        return taskService.updateTask(task);
    }

}
