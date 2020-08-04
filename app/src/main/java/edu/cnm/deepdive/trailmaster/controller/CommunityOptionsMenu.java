package edu.cnm.deepdive.trailmaster.controller;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.cnm.deepdive.trailmaster.R;

public class CommunityOptionsMenu extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.community_options_menu);

    Button butt6 = findViewById(R.id.butt6);


    butt6.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent int6 = new Intent(CommunityOptionsMenu.this, BulletinBoard.class);
        startActivity(int6);
      }
    });


   }
}