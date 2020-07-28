package edu.cnm.deepdive.trailmaster.model;

import java.io.Serializable;

public class Trail implements Serializable {

  private static final long serialVersionUID = //TODO Add when video is up from 2/27

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
  private Geometry geometry;

}
