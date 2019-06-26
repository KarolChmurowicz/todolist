package com.kchmurowicz.todolist.service;

import com.kchmurowicz.todolist.dto.TaskDto;
import com.kchmurowicz.todolist.models.Task;
import com.kchmurowicz.todolist.models.TaskList;
import com.kchmurowicz.todolist.models.User;
import com.kchmurowicz.todolist.repository.TaskRepository;
import com.kchmurowicz.todolist.security.ExtendedUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final TaskRepository taskRepository;
    private final TaskListService taskListService;
    private final UserService userService;

    public TaskService(TaskRepository taskRepository, TaskListService taskListService, UserService userService) {
        this.taskRepository = taskRepository;
        this.taskListService = taskListService;
        this.userService = userService;
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Task createTask(TaskDto taskDto, Principal principal) {
        Optional<TaskList> taskList = taskListService.findById(taskDto.getTaskListId());
        Long userId = ((ExtendedUser) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        Optional<User> user = userService.findById(userId);
        if (taskList.isPresent()) {
            LOGGER.debug("TaskList has been found ");
            Task task = new Task();
            task.setName(taskDto.getName());
            task.setDescription(taskDto.getDescription());
            task.setTaskList(taskList.get());
            task.setUser(user.get());
            LOGGER.debug("created a Task");
            return save(task);
        }
        throw new IllegalArgumentException("Could not find TaskList with given id.");
    }

    public void deleteTask(Long deletedTaskId, Principal principal) throws IllegalAccessException {
        LOGGER.debug("deleting a Task with ID {}", deletedTaskId);
        try {
            Long userId = ((ExtendedUser) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
            Task existingTask = taskRepository.getOne(deletedTaskId);
            if (existingTask.getUser().getId().equals(userId)) {
                taskRepository.deleteById(deletedTaskId);
            } else {
                throw new IllegalAccessException("Session User does not match with User assigned to the Task ");
            }
        } catch (EntityNotFoundException ignored) {
        }
    }

    public List<Task> findAll() {
        LOGGER.debug("getting all Task");
        return taskRepository.findAll();
    }

    public List<Task> findUsersTasks(Principal principal) {
        Long userId = ((ExtendedUser) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        Optional<User> user = userService.findById(userId);
        LOGGER.debug("getting a list of Task belonging to a specific authorized User");
        return taskRepository.findByUser(user.orElse(null));
    }

    public Task updateTask(TaskDto taskDto, Long taskId, Principal principal) throws IllegalAccessException {
        if (taskId.equals(taskDto.getId())) {
            LOGGER.debug("Task ID matches TaskDto ID");
            Optional<Task> task = taskRepository.findById(taskId);
            Task existingTask = task.orElseThrow(() -> new IllegalArgumentException("Could not find Task with given id"));
            Long userId = ((ExtendedUser) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
            if (existingTask.getUser().getId().equals(userId)) {
                existingTask.setName(taskDto.getName());
                existingTask.setDescription(taskDto.getDescription());
                LOGGER.debug("updated a Task");
                return save(existingTask);
            } else {
                throw new IllegalAccessException("User is not authorized to update this Task");
            }
        } else {
            throw new IllegalArgumentException("Id from url and body do not match.");
        }
    }
}
