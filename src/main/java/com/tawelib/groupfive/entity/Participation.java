package com.tawelib.groupfive.entity;

import java.io.Serializable;

public class Participation implements Serializable {
    private String username;
    private String eventId;

    public Participation(String username, String eventId){
        this.username = username;
        this.eventId = eventId;
    }

    public String getUsername(){
        return username;
    }
    public String getEventId(){
        return eventId;
    }

}
