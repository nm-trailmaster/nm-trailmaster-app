package edu.cnm.deepdive.trailmaster.controller;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.cnm.deepdive.trailmaster.R;

public class Activity1 extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_1);

    Button butt4 = findViewById(R.id.butt4);

    butt4.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent int4 = new Intent(Activity1.this,Activity4.class);
        startActivity(int4);
      }
    });


  }
}
