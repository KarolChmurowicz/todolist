package com.kchmurowicz.todolist.service;

import com.kchmurowicz.todolist.models.TaskList;
import com.kchmurowicz.todolist.models.User;
import com.kchmurowicz.todolist.repository.TaskListRepository;
import com.kchmurowicz.todolist.security.ExtendedUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        LOGGER.debug("saving a TaskList with name{}", taskList.getName());
        return taskListRepository.save(taskList);
    }

    public TaskList createTaskList(TaskList taskList, Principal principal) {
        LOGGER.debug("creating a TaskList with name{}", taskList.getName());
        Long userId = ((ExtendedUser) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        Optional<User> user = userService.findById(userId);
        user.ifPresent(taskList::setUser);
        return save(taskList);
    }

    public List<TaskList> findAll() {
        LOGGER.debug("Getting all TaskLists");
        return taskListRepository.findAll();
    }

    public List<TaskList> findUsersLists(Principal principal) {
        Long userId = ((ExtendedUser) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        Optional<User> user = userService.findById(userId);
        return taskListRepository.findByUser(user.orElse(null));
    }

    public void delete(Long deletedTaskListId, Principal principal) throws IllegalAccessException {
        LOGGER.debug("Deleting a TaskList with id {}", deletedTaskListId);
        Long userId = ((ExtendedUser) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        TaskList existingTaskList = taskListRepository.getOne(deletedTaskListId);
        if (existingTaskList.getUser().getId().equals(userId)) {
            taskListRepository.deleteById(deletedTaskListId);
        } else {
            throw new IllegalAccessException("Session user does not match user assigned to the task list");
        }
    }

    public TaskList update(TaskList updatedTaskList, Principal principal) throws IllegalAccessException {
        LOGGER.debug("updating a TaskList with name {}", updatedTaskList.getName());
        Long userId = ((ExtendedUser) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        TaskList existingTaskList = taskListRepository.getOne(updatedTaskList.getId());
        if (existingTaskList.getUser().getId().equals(userId)) {
            existingTaskList.setName(updatedTaskList.getName());
            return save(existingTaskList);
        }
        throw new IllegalAccessException("Session user does not match user assigned to the task list");
    }


    public Optional<TaskList> findById(Long taskListId) {
        LOGGER.debug("finding a TaskList with id {}", taskListId);
        return taskListRepository.findById(taskListId);
    }
}
