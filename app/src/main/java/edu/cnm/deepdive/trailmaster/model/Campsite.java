package edu.cnm.deepdive.trailmaster.model;

import com.google.gson.annotations.Expose;
import java.io.Serializable;

public class Campsite implements Serializable {

  private static final long serialVersionUID = //TODO add from video on 7/27

  @Expose
  private long id;

  @Expose
  private String name;

  @Expose
  private String description;

  @Expose
  private String imageUrl;

  @Expose
  private User creator;

  @Expose
  private LongLat longLat;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public User getCreator() {
    return creator;
  }

  public void setCreator(User creator) {
    this.creator = creator;
  }

  public LongLat getLongLat() {
    return longLat;
  }

  public void setLongLat(LongLat longLat) {
    this.longLat = longLat;
  }

  @Override
  public String toString() {
    return "Campsite{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", imageUrl='" + imageUrl + '\'' +
        ", creator=" + creator +
        ", longLat=" + longLat +
        '}';
  }

}
