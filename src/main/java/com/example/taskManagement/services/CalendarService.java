package com.example.taskManagement.services;

import com.example.taskManagement.entity.Task;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CalendarService {

    @Autowired
    private Calendar calendar;

    public void createCalendarEventFromTask(Task task) throws IOException {

        Event event = new Event()
                .setSummary(task.getTitle())
                .setDescription(task.getDescription());

        // Set start and end date/time based on task due date
        DateTime start = new DateTime(task.getCreatedAt().getValue());
        event.setStart(new EventDateTime().setDateTime(start));
        event.setEnd(new EventDateTime().setDateTime(task.getDueDate()));

        calendar.events().insert("msamir25121997@gmail.com", event).execute();
    }
}
