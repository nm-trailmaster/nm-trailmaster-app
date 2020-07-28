package edu.cnm.deepdive.trailmaster;

import android.app.Application;
import edu.cnm.deepdive.trailmaster.service.GoogleSignInService;

public class TrailApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    GoogleSignInService.setContext(this);
  }
}
