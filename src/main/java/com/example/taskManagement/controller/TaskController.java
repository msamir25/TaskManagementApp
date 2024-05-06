package com.example.taskManagement.controller;

import com.example.taskManagement.dto.TaskDto;
import com.example.taskManagement.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/task")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.createTask(taskDto));
    }

    @GetMapping("/task")
    public  ResponseEntity<List<TaskDto>> getAllTasks(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto , @PathVariable(name = "id") Long id){
        return ResponseEntity.ok(taskService.updateTaskById(taskDto, id));
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable(name = "id") Long id){
        taskService.deleteById(id);
        return ResponseEntity.ok("Task is Successfully Deleted");
    }

}
