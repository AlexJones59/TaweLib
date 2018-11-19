package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.User;
import java.util.List;

public class LibrarianRepository extends UserRepository {

  @Override
  public User authenticate(String username) {
    return null;
  }

  @Override
  protected void generateUsername(User user) {

  }

  @Override
  public List getAll() {
    return null;
  }

  @Override
  public void add(Object o) {

  }
}
