package edu.cnm.deepdive.trailmaster.service;

import android.content.Context;
import edu.cnm.deepdive.trailmaster.model.Trail;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TrailRepository {

  private static final String AUTH_HEADER_FORMAT = "Bearer %s";
  private static final int NETWORK_POOL_SIZE = 4;

  private final Context context;
  private final TrailMasterCloudService cloudService;
  private final ExecutorService networkPool;

  public TrailRepository(Context context) {
    this.context = context;
    cloudService = TrailMasterCloudService.getInstance();
    networkPool = Executors.newFixedThreadPool(NETWORK_POOL_SIZE);
  }

  public Single<List<Trail>> getAll(String idToken) {
    return cloudService.getAll()
        .subscribeOn(Schedulers.from(networkPool));
  }

  public Single<Trail> get(String idToken, long id) {
    return cloudService.get(id)
        .subscribeOn(Schedulers.from(networkPool));
  }
  public Single<Trail> save(String idToken, Trail trail) {
    Single<Trail> remoteTask = (trail.getId() == 0)
        ? cloudService.post(getHeader(idToken), trail)
        : cloudService.put(getHeader(idToken), trail.getId(), trail);
    return remoteTask
        .subscribeOn(Schedulers.from(networkPool));
  }

  public Completable delete(String idToken, Trail trail) {
    Completable remoteTask = (trail.getId() == 0)
        ? cloudService.delete(getHeader(idToken), trail.getId())
        : Completable.complete();
    return remoteTask
        .subscribeOn(Schedulers.from(networkPool));
  }
  // TODO add more methods as necessary. work on viewmodel to invoke methods.
  private String getHeader(String idToken) {
    return String.format(AUTH_HEADER_FORMAT, idToken);
  }
}
