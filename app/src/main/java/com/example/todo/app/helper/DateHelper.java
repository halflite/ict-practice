package com.example.todo.app.helper;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="date")
public class DateHelper {

    private ZoneId zone = ZoneId.systemDefault(); 
    
    public LocalDateTime now() {
        return LocalDateTime.now(zone);
    }
}
