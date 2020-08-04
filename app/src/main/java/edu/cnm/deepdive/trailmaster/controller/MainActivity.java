package edu.cnm.deepdive.trailmaster.controller;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.trailmaster.R;
import edu.cnm.deepdive.trailmaster.service.GoogleSignInService;
import edu.cnm.deepdive.trailmaster.service.PermissionsService;
import edu.cnm.deepdive.trailmaster.ui.CampOptionsMenu;
import edu.cnm.deepdive.trailmaster.ui.TrailOptionsMenu;
import edu.cnm.deepdive.trailmaster.viewmodel.MainViewModel;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
  private static final int PERMISSIONS_REQUEST_CODE = 999;

  private final PermissionsService permissionsService = PermissionsService.getInstance();
  private GoogleSignInService signInService;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    setupObservers();
    observePermissions();
    checkPermissionsOnce();

    Button butt1 = findViewById(R.id.butt1);
    Button butt2 = findViewById(R.id.butt2);
    Button butt3 = findViewById(R.id.butt3);

    butt1.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent int1 = new Intent(MainActivity.this, TrailOptionsMenu.class);
        startActivity(int1);
      }
    });

    butt2.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent int2 = new Intent(MainActivity.this, CampOptionsMenu.class);
        startActivity(int2);
      }
    });

    butt3.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent int3 = new Intent(MainActivity.this, CommunityOptionsMenu.class);
        startActivity(int3);
      }
    });


  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    getMenuInflater().inflate(R.menu.options, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    boolean handled = true;
    //noinspection SwitchStatementWithTooFewBranches
    switch (item.getItemId()) {
      case R.id.sign_out:
        signInService.signOut().addOnCompleteListener((ignored) -> switchToLogin());
        break;
      default:
        handled = super.onOptionsItemSelected(item);
    }
    return handled;
  }

  private void setupObservers() {
    MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    getLifecycle().addObserver(viewModel);
    viewModel.getThrowable().observe(this, (throwable) -> {
      if (throwable != null) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show();
      }
    });
    signInService = GoogleSignInService.getInstance();
  }

  private void switchToLogin() {
    Intent intent = new Intent(this, LoginActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }


  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    if (requestCode == PERMISSIONS_REQUEST_CODE) {
      permissionsService.updatePermissions(permissions, grantResults);
    } else {
      super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
  }

  private void observePermissions() {
    permissionsService.getPermissions().observe(this, (perms) -> {
      // Display the permissions in a list view.
      ListView permissions = findViewById(R.id.permissions);
      ArrayAdapter<String> adapter =
          new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new LinkedList<>(perms));
      permissions.setAdapter(adapter);
    });
  }

  private void checkPermissionsOnce() {
    MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    // Observe a flag indicating whether permissions have been checked previously.
    viewModel.getPermissionsChecked().observe(this, (checked) -> {
      // If permissions have not yet been checked, do so; then set the flag accordingly.
      if (!checked) {
        viewModel.setPermissionsChecked(true);
        permissionsService.checkPermissions(this, PERMISSIONS_REQUEST_CODE);
      }
    });
  }

}
