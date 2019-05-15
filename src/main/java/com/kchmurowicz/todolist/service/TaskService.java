package com.kchmurowicz.todolist.service;

import com.kchmurowicz.todolist.dto.TaskDto;
import com.kchmurowicz.todolist.models.Task;
import com.kchmurowicz.todolist.models.TaskList;
import com.kchmurowicz.todolist.repository.TaskRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    private final TaskRepository taskRepository;
    private final TaskListService taskListService;

    public TaskService(TaskRepository taskRepository, TaskListService taskListService) {
        this.taskRepository = taskRepository;
        this.taskListService = taskListService;
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Task createTask(TaskDto taskDto) {

        Optional<TaskList> taskList = taskListService.findById(taskDto.getTaskListId());

        if (taskList.isPresent()) {
            LOGGER.debug("taskList has been found ");
            Task task = new Task();
            task.setName(taskDto.getName());
            task.setDescription(taskDto.getDescription());
            task.setTaskList(taskList.get());
            LOGGER.debug("created a task");
            return save(task);
        }

        throw new IllegalArgumentException("Could not find task list with given id.");
    }

    public void deleteTask(Long taskId) {
        LOGGER.debug("deleting a task with ID {}", taskId);
        taskRepository.deleteById(taskId);
    }

    public List<Task> findAll() {
        LOGGER.debug("getting all Tasks");
        return taskRepository.findAll();
    }

    public Task updateTask(TaskDto taskDto, Long taskId) {
        if (taskId.equals(taskDto.getId())) {
            LOGGER.debug("task ID matches taskDto ID");
            Optional<Task> task = taskRepository.findById(taskId);

            Task existingTask = task.orElseThrow(() -> new IllegalArgumentException("Could not find Task with given id"));

            existingTask.setName(taskDto.getName());
            existingTask.setDescription(taskDto.getDescription());
            LOGGER.debug("updated a task");
            return save(existingTask);
        } else {
            throw new IllegalArgumentException("Id from url and body do not match.");
        }
    }

}


