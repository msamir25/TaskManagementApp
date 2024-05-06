package com.example.taskManagement.entity;

import com.example.taskManagement.dto.TaskDto;
import com.google.api.client.util.DateTime;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private DateTime dueDate;

    private String priority;

    private DateTime createdAt;

    private DateTime updatedAt;

    public TaskDto getDto(){
        TaskDto taskDto = new TaskDto();
        taskDto.setId(id);
        taskDto.setTitle(title);
        taskDto.setDescription(description);
        taskDto.setDueDate(dueDate);
        taskDto.setCreatedAt(createdAt);
        taskDto.setUpdatedAt(updatedAt);
        taskDto.setPriority(priority);
        return taskDto;
    }

}
