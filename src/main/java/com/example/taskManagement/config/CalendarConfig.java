package com.example.taskManagement.config;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;

import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

@Configuration
public class CalendarConfig {

    @Bean
    public GoogleCredentials credentials() throws IOException {
        return GoogleCredentials.fromStream(
                new FileInputStream("credentials.json"))
                .createScoped(Collections.singletonList("https://www.googleapis.com/auth/calendar"));
    }

    @Bean
    public Calendar service(GoogleCredentials credentials) throws IOException {
        // Create HttpTransport and JsonFactory instances
        final HttpTransport httpTransport = new NetHttpTransport();
        final JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        // Build calendar service
        return new Calendar.Builder(httpTransport, jsonFactory, (HttpRequestInitializer) credentials)
                .setApplicationName("task-management-app")
                .build();
    }

}
