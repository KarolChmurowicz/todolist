package com.kchmurowicz.todolist.service;

import com.kchmurowicz.todolist.models.TaskList;
import com.kchmurowicz.todolist.models.User;
import com.kchmurowicz.todolist.repository.TaskListRepository;
import com.kchmurowicz.todolist.security.ExtendedUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class TaskListService {

    private final TaskListRepository taskListRepository;

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final UserService userService;


    public TaskListService(TaskListRepository taskListRepository, UserService userService) {
        this.taskListRepository = taskListRepository;
        this.userService = userService;
    }

    public TaskList save(TaskList taskList) {

        LOGGER.debug("creating a TaskList with name{}", taskList.getName());
        return taskListRepository.save(taskList);
    }

    public TaskList createTaskList(TaskList taskList, Principal principal) {
        Long userId = ((ExtendedUser) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        Optional<User> user = userService.findById(userId);
        user.ifPresent(taskList::setUser);
        return save(taskList);
    }

    public List<TaskList> findAll() {

        LOGGER.debug("Getting all TaskLists");
        return taskListRepository.findAll();
    }

    public List<TaskList> findUsersLists(Principal principal){
        Long userId = ((ExtendedUser) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        Optional<User> user = userService.findById(userId);

        return taskListRepository.findByUser(user.orElse(null));

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
