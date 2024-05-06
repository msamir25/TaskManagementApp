package com.example.taskManagement.services;

import com.example.taskManagement.dto.TaskDto;

import java.util.List;

public interface TaskService {

    TaskDto createTask(TaskDto taskDto);

    List<TaskDto> getAllTasks();

    TaskDto getTaskById(Long id);


    TaskDto updateTaskById(TaskDto taskDto , Long id);

    void deleteById(Long id);

}
