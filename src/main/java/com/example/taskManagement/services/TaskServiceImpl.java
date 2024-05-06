package com.example.taskManagement.services;

import com.example.taskManagement.dto.TaskDto;
import com.example.taskManagement.entity.Task;
import com.example.taskManagement.exceptions.ResourceNotFoundException;
import com.example.taskManagement.repository.TaskRepository;
import com.google.api.client.util.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{

    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @CacheEvict(value = {"getTaskById" , "getAllTasks"} , key = "#root.methodName")
    public TaskDto createTask(TaskDto taskDto) {
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setDueDate(taskDto.getDueDate());
        task.setCreatedAt(new DateTime("yyyy-MM-DD HH:mm:ss"));
        task.setUpdatedAt(new DateTime("yyyy-MM-DD HH:mm:ss"));
        task.setPriority(taskDto.getPriority());

        Task savedTask = taskRepository.save(task);
        return savedTask.getDto();
    }

    @Cacheable(value ="getAllTasks", key = "#root.methodName")
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(Task::getDto).collect(Collectors.toList());
    }

    @Cacheable(value ="getTaskById", key = "#id")
    public TaskDto getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("task" , " id " , id));

        return task.getDto();
    }

    @CacheEvict(value = {"getTaskById" , "getAllTasks"} , key = "#root.methodName")
    public TaskDto updateTaskById(TaskDto taskDto, Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("task" , " id " , id));

        task.setPriority(taskDto.getPriority());
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setUpdatedAt(new DateTime("yyyy-MM-DD HH:mm:ss"));
        task.setCreatedAt(task.getCreatedAt());
        task.setDueDate(taskDto.getDueDate());
        Task savedTask = taskRepository.save(task);

        return savedTask.getDto();
    }

    @CacheEvict(value = {"getTaskById" , "getAllTasks"} , key = "#root.methodName")
    public void deleteById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("task" , " id " , id));
        taskRepository.delete(task);
    }
}

















