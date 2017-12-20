package com.example.elvina.quickshop;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import static android.graphics.Color.parseColor;

public class profileModification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_modification);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(parseColor("#3F3F3F")));
    }
}
