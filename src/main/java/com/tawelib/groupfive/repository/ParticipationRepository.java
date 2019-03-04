package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Event;
import com.tawelib.groupfive.entity.Participation;
import com.tawelib.groupfive.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ParticipationRepository implements BaseRepository<Participation> {

  private ArrayList<Participation> participation;

  public ParticipationRepository() {
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

  public int getNumberOfParticipants(Event event) {
    int count = 0;
    for (Participation i : participation) {
      if (event.getEventId() == i.getEventId()) {
        count++;
      }
    }
    return count;

  }

  public boolean doesParticipate(Event event, User user) {
    boolean result = false;
    for (Participation i : participation) {
      if (event.getEventId() == i.getEventId()) {
        if (user.getUsername() == i.getUsername()) {
          result = true;
        }
      }
    }
    return result;
  }


}
