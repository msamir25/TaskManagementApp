package com.example.taskManagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.api.client.util.DateTime;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDto {

    private Long id;

    private String title;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private DateTime dueDate;

    private String priority;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private DateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private DateTime updatedAt;
}
