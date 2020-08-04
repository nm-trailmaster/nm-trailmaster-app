package edu.cnm.deepdive.trailmaster.controller;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.trailmaster.R;
import edu.cnm.deepdive.trailmaster.model.Trail;
import edu.cnm.deepdive.trailmaster.view.TrailAdapter;
import edu.cnm.deepdive.trailmaster.viewmodel.MainViewModel;

public class StockTrails extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.stock_trails);


    Button buttA = findViewById(R.id.butt_a);
    Button buttB = findViewById(R.id.butt_b);
    Button buttC = findViewById(R.id.butt_c);
    Button buttD = findViewById(R.id.butt_d);
    Button buttE = findViewById(R.id.butt_e);

    buttA.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(StockTrails.this,Activity14.class);
        startActivity(intent);
      }
    });

    buttB.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent2 = new Intent(StockTrails.this,Activity15.class);
        startActivity(intent2);
      }
    });

    buttC.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent3 = new Intent(StockTrails.this,Activity16.class);
        startActivity(intent3);
      }
    });

    buttD.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent4 = new Intent(StockTrails.this,Activity17.class);
        startActivity(intent4);
      }
    });

    buttE.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent5 = new Intent(StockTrails.this,Activity18.class);
        startActivity(intent5);
      }
    });


  }
}