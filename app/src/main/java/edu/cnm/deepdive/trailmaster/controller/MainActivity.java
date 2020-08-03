package edu.cnm.deepdive.trailmaster.controller;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.trailmaster.R;
import edu.cnm.deepdive.trailmaster.service.GoogleSignInService;
import edu.cnm.deepdive.trailmaster.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {
  private GoogleSignInService signInService;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
//    setupObservers();

    Button butt1 = findViewById(R.id.butt1);
    Button butt2 = findViewById(R.id.butt2);
    Button butt3 = findViewById(R.id.butt3);

    butt1.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent int1 = new Intent(MainActivity.this,Activity1.class);
        startActivity(int1);
      }
    });

    butt2.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent int2 = new Intent(MainActivity.this,Activity2.class);
        startActivity(int2);
      }
    });

    butt3.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent int3 = new Intent(MainActivity.this,Activity3.class);
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


}
