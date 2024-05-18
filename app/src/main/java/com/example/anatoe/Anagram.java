package com.example.anatoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Anagram extends AppCompatActivity {
    Button Easy;
    Button Hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anagram);

        Easy = (Button) findViewById(R.id.buttonEasy);
        Hard = (Button) findViewById(R.id.buttonHard);

        Easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate to EasyActivity
                Intent EasyIntent = new Intent(Anagram.this, EasyAnagram.class);
                startActivity(EasyIntent);
            }
        });

        Hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to navigate to HardActivity
                Intent HardIntent = new Intent(Anagram.this, HardAnagram.class);
                startActivity(HardIntent);
            }
        });
    }
}
