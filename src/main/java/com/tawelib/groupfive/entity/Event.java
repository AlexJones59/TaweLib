package com.tawelib.groupfive.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

public class Event implements Serializable {
    private String eventId;
    private String eventName;
    private LocalDateTime eventDate;
    private int capacity;
    private String description;

    public Event(String eventId, String eventName, LocalDateTime eventDate,
                 int capacity, String description){
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.capacity = capacity;
        this.description = description;
    }

    public String getEventId(){
        return eventId;
    }
    public String getEventName(){
        return eventName;
    }
    public LocalDateTime getEventDate(){
        return eventDate;
    }
    public int getCapacity(){
        return capacity;
    }

    public String getDescription() {
        return description;
    }
}
