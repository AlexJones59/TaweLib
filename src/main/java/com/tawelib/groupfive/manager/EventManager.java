package com.tawelib.groupfive.manager;
import com.tawelib.groupfive.entity.User;
import com.tawelib.groupfive.entity.Event;
import com.tawelib.groupfive.entity.Participation;
import com.tawelib.groupfive.entity.Library;

import java.util.ArrayList;

public class EventManager {

    public static void addEvent(Library library, Event event){
        library.getEventRepository().add(event);
    }

    public static void joinEvent(Library library, User user, Event event){
        Participation participation = new Participation(user.getUsername(), event.getEventId());
        library.getParticipationRepository().add(participation);
    }
    public static boolean hasJoined(Library library, User user, Event event){

    }

    private boolean eventFull(Library library, Event event){
        
        int participating = library.getParticipationRepository().getNumberOfParticipants(;
        return participating == event.getCapacity();

    }

}
