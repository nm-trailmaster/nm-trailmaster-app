package edu.cnm.deepdive.trailmaster.controller;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.cnm.deepdive.trailmaster.R;

public class Activity3 extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_3);

    Button butt6 = findViewById(R.id.butt6);
//    Button butt7 = findViewById(R.id.butt7);

    butt6.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent int6 = new Intent(Activity3.this, Activity6.class);
        startActivity(int6);
      }
    });

//    butt7.setOnClickListener(new OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        Intent int7 = new Intent(Activity3.this, Activity7.class);
//        startActivity(int7);
//      }
//    });
//
   }
}