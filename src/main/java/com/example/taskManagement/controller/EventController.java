package com.example.taskManagement.controller;

import com.example.taskManagement.entity.Task;
import com.example.taskManagement.services.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;

@ControllerAdvice
public class EventController {

    @Autowired(required = false)
    private CalendarService calendarService;

    @ModelAttribute
    public void afterTaskCreation(@ModelAttribute Task task) throws IOException {
        if (calendarService != null) {
            calendarService.createCalendarEventFromTask(task);
        }
    }
}
