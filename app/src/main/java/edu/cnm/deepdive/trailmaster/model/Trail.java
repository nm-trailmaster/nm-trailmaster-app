package edu.cnm.deepdive.trailmaster.model;

import androidx.annotation.Nullable;
import com.google.gson.annotations.Expose;
import java.io.Serializable;

public class Trail implements Serializable {

//  private static final long serialVersionUID = //TODO Add when video is up from 2/27

  @Expose
  private long id;

  @Expose
  @Nullable
  private String name;

  @Expose
  @Nullable
  private String comment;

  @Expose
  @Nullable
  private User author;

  @Expose
  private double latitude;

  @Expose
  private double longitude;

  @Expose
  private int rating;

  @Expose
  private Geometry geometry;

//  public static long getSerialVersionUID() {
//    return serialVersionUID;
//  }

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

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public Geometry getGeometry() {
    return geometry;
  }

  public void setGeometry(Geometry geometry) {
    this.geometry = geometry;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  @Override
  public String toString() {
    return "Trail{" +
        ", name" + name + '\'' +
        ", comment='" + comment + '\'' +
        ", author=" + author +
        ", latitude =" + latitude +
        ", longitude =" + longitude +
        '}';
  }

}
