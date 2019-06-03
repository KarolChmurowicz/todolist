package com.kchmurowicz.todolist.controller;

import com.kchmurowicz.todolist.models.TaskList;
import com.kchmurowicz.todolist.service.TaskListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private TaskListService taskListService;

    public AdminController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @GetMapping
    public @ResponseBody
    List<TaskList> getAllTasksLists(){
        return taskListService.findAll();
    }

}
