package edu.cnm.deepdive.trailmaster.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.cnm.deepdive.trailmaster.R;
import edu.cnm.deepdive.trailmaster.controller.StockCampsites;

public class CampOptionsMenu extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.camp_options_menu);

    Button butt5 = findViewById(R.id.butt5);
    Button butt13 = findViewById(R.id.butt13);

    butt5.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent int5 = new Intent(CampOptionsMenu.this, StockCampsites.class);
        startActivity(int5);
      }
    });


    butt13.setOnClickListener(v -> {
      Intent int13 = new Intent(CampOptionsMenu.this, CampEssentialsInfo.class);
      startActivity(int13);
    });



  }
}