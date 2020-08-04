package edu.cnm.deepdive.trailmaster.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.cnm.deepdive.trailmaster.R;
import edu.cnm.deepdive.trailmaster.controller.StockTrails;

public class TrailOptionsMenu extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.trail_options_menu);

    Button butt4 = findViewById(R.id.butt4);

    butt4.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent int4 = new Intent(TrailOptionsMenu.this, StockTrails.class);
        startActivity(int4);
      }
    });


  }
}
