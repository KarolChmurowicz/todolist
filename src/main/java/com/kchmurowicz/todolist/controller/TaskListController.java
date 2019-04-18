package com.kchmurowicz.todolist.controller;

import com.kchmurowicz.todolist.models.TaskList;
import com.kchmurowicz.todolist.service.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/lists")
public class TaskListController {

    private final TaskListService taskListService;

    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @GetMapping()
    public @ResponseBody
    List<TaskList> getTasksList() {
        return taskListService.findAll();
    }

    @PostMapping()
    public @ResponseBody
    TaskList createTaskList(@RequestBody TaskList taskList) {
        return taskListService.save(taskList);
    }

    @DeleteMapping()
    public void deleteTasksList(@RequestParam(name = "id") Long taskListId){
        taskListService.delete(taskListId);
    }

    @PutMapping()
    public @ResponseBody TaskList updateTasksList(@RequestBody TaskList taskList) {return taskListService.update(taskList);}


}
