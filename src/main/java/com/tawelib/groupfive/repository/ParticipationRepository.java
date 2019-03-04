package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Participation;

import java.util.ArrayList;
import java.util.List;

public class ParticipationRepository implements BaseRepository<Participation> {
    private ArrayList<Participation> participation;

    public ParticipationRepository(){
        participation = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Participation> getAll() {
        return participation;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Participation participation) {
        if (!this.participation.contains(participation)) {
            this.participation.add(participation);
        }
    }

    public int getNumberOfParticipants(String eventID){
        int count = 0;
        for(Participation i : participation){
            if(eventID == i.getEventId()){
                count++;
            }
        }
        return count;

    }
    public boolean doesParticipate(String eventID, String username){
        boolean result = false;
        for(Participation i: participation){
            if (eventID == i.getEventId()){
                if(username == i.getUsername()){
                    result = true;
                }
            }
        }
        return result;
    }


}
