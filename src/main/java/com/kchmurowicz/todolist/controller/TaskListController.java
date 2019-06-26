package com.kchmurowicz.todolist.controller;

import com.kchmurowicz.todolist.models.TaskList;
import com.kchmurowicz.todolist.service.TaskListService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/lists")
public class TaskListController {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final TaskListService taskListService;

    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @GetMapping()
    public @ResponseBody
    List<TaskList> getTasksList(Principal principal) {
        LOGGER.debug("Received a user request to get all TasksList");
        return taskListService.findUsersLists(principal);
    }

    @PostMapping()
    public @ResponseBody
    TaskList createTaskList(@Valid @RequestBody TaskList taskList, Principal principal) {
        LOGGER.debug("Received a user request to create a TaskList with name {}", taskList.getName());
        TaskList createdTaskList = taskListService.createTaskList(taskList,principal);
        LOGGER.debug("Returning created TaskList with name {}", createdTaskList.getName());
        return createdTaskList;
    }

    @DeleteMapping()
    public void deleteTasksList(@RequestParam(name = "id") Long taskListId, Principal principal) throws IllegalAccessException {
        LOGGER.debug("Received a user request to delete a TaskList with id {}", taskListId);
        taskListService.delete(taskListId,principal);
    }

    @PutMapping()
    public @ResponseBody
    TaskList updateTasksList(@RequestBody TaskList taskList, Principal principal) throws IllegalAccessException {
        LOGGER.debug("Received a user request to update a TaskList with name {}", taskList.getName());
        TaskList updatedTaskList = taskListService.update(taskList, principal);
        LOGGER.debug("Returning updated TaskList with name {}", updatedTaskList.getName());
        return updatedTaskList;
    }


}
