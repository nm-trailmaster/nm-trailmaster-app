package edu.cnm.deepdive.trailmaster.model;

import com.google.gson.annotations.Expose;
import java.io.Serializable;

public class User implements Serializable {

//  private static final long serialVersionUID = //TODO add from video from 7/27

  @Expose
  private String username;
//
//  public User() {
//
//  }

  public User(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}
