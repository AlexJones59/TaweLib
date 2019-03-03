package com.tawelib.groupfive.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

public class Event implements Serializable {
    private String eventId;
    private String eventName;
    private LocalDateTime eventDate;
    private String description;
    private int capasity;

}
