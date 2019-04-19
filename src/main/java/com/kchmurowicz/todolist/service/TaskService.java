package com.kchmurowicz.todolist.service;

import com.kchmurowicz.todolist.dto.TaskDto;
import com.kchmurowicz.todolist.models.Task;
import com.kchmurowicz.todolist.models.TaskList;
import com.kchmurowicz.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskListService taskListService;

    public TaskService(TaskRepository taskRepository, TaskListService taskListService) {
        this.taskRepository = taskRepository;
        this.taskListService = taskListService;
    }

    public Task save(Task task) {return  taskRepository.save(task);}

    public Task createTask(TaskDto taskDto) {
        Optional<TaskList> taskList = taskListService.findById(taskDto.getTaskListId());

        if(taskList.isPresent()){
            Task task = new Task();
            task.setName(taskDto.getName());
            task.setDescription(taskDto.getDescription());
            task.setTaskList(taskList.get());

            return save(task);
        }

        throw new IllegalArgumentException("Could not find task list with given id.");
    }

    public void deleteTask(Long taskId){
        taskRepository.deleteById(taskId);
    }

    public List<Task> findAll(){ return taskRepository.findAll();    }

}
