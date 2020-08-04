package edu.cnm.deepdive.trailmaster.controller;

import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.trailmaster.R;
import edu.cnm.deepdive.trailmaster.model.Trail;
import edu.cnm.deepdive.trailmaster.view.TrailAdapter;
import edu.cnm.deepdive.trailmaster.view.TrailAdapter.OnClickListener;
import edu.cnm.deepdive.trailmaster.viewmodel.MainViewModel;

public class StockTrails extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.stock_trails);
  }

}