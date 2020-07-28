package edu.cnm.deepdive.trailmaster.model;

import com.google.gson.annotations.Expose;
import java.util.Date;

public class UserCharacteristics {

  @Expose
  private String username;

  @Expose
  private User user;

  @Expose
  private Date created;

  @Expose
  private String firstName;

  @Expose
  private String lastName;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

}
