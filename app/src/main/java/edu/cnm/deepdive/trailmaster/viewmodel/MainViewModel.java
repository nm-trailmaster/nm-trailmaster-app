package edu.cnm.deepdive.trailmaster.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import edu.cnm.deepdive.trailmaster.R;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import edu.cnm.deepdive.trailmaster.model.Trail;
import edu.cnm.deepdive.trailmaster.service.GoogleSignInService;
import edu.cnm.deepdive.trailmaster.service.TrailRepository;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.List;

public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

  private final TrailRepository trailRepository;
  private final MutableLiveData<List<Trail>> trails;
  private final MutableLiveData<Trail> trail;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;
  private final GoogleSignInService signInService;
  private final MutableLiveData<Boolean> permissionsChecked;

  public MainViewModel(@NonNull Application application) {
    super(application);
    trailRepository = new TrailRepository(application);
    trails = new MutableLiveData<>();
    trail = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    signInService = GoogleSignInService.getInstance();
    permissionsChecked = new MutableLiveData<>(false);
    loadTrails();
  }

  public LiveData<List<Trail>> getTrails() {
    return trails;
  }

  public LiveData<Trail> getTrail() {
    return trail;
  }

  // TODO refer to quotes client to finish.
  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  private String getAuthorizationHeader(GoogleSignInAccount account) {
    String token = getApplication().getString(R.string.oauth_header, account.getIdToken());
    Log.d("OAuth2.0 token", token);
    return token;
  }

  public LiveData<Boolean> getPermissionsChecked() {
    return permissionsChecked;
  }

  public void setPermissionsChecked(boolean checked) {
    permissionsChecked.setValue(checked);
  }

  public void setTrailId(long id) {
    refreshAndExecute((account) ->
        trailRepository.get(account.getIdToken(), id)
            .subscribe(
                trail::postValue,
                throwable::postValue
            )
    );
  }

  public void save(Trail trail) {
    refreshAndExecute((account) ->
        trailRepository.save(account.getIdToken(), trail)
            .subscribe(
                (t) -> loadTrails(),
                throwable::postValue
            )
    );
  }

  public void delete(Trail trail) {
    refreshAndExecute((account) ->
        trailRepository.delete(account.getIdToken(), trail)
            .subscribe(
                this::loadTrails,
                throwable::postValue
            )
    );
  }

  private void loadTrails() {
    refreshAndExecute((account) ->
        trailRepository.getAll(account.getIdToken())
            .subscribe(
                trails::postValue,
                throwable::postValue
            )
    );
  }

  private void refreshAndExecute(AuthenticatedTask task) {
    signInService.refresh()
        .addOnSuccessListener((account) -> pending.add(task.execute(account)))
        .addOnFailureListener(throwable::postValue);
  }



  public interface AuthenticatedTask {

    Disposable execute(GoogleSignInAccount account);
  }

}
