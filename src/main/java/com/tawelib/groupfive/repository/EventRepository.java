package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Event;

import java.util.ArrayList;
import java.util.List;

public class EventRepository implements BaseRepository<Event> {

    private ArrayList<Event> events;

    public EventRepository() {
        events = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Event> getAll() {
        return events;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Event event) {
        if (!events.contains(event)) {
            events.add(event);
        }
    }

}


