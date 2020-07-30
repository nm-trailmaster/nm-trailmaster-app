package edu.cnm.deepdive.trailmaster.controller;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.cnm.deepdive.trailmaster.R;

public class Activity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);


        Button butt8 = findViewById(R.id.butt8);
        Button butt9 = findViewById(R.id.butt9);
        Button butt10 = findViewById(R.id.butt10);
        Button butt11 = findViewById(R.id.butt11);
        Button butt12 = findViewById(R.id.butt12);

        butt8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int8 = new Intent(Activity5.this,Activity8.class);
                startActivity(int8);
            }
        });

        butt9.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int9 = new Intent(Activity5.this,Activity9.class);
                startActivity(int9);
            }
        });

        butt10.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int10 = new Intent(Activity5.this,Activity10.class);
                startActivity(int10);
            }
        });

        butt11.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int11 = new Intent(Activity5.this,Activity11.class);
                startActivity(int11);
            }
        });

        butt12.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int12 = new Intent(Activity5.this,Activity12.class);
                startActivity(int12);
            }
        });


    }
}