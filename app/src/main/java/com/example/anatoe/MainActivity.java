package com.example.anatoe;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Button anagramButton, ticTacToeButton, exitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anagramButton = (Button) findViewById(R.id.buttonAnagram);
        ticTacToeButton = (Button) findViewById(R.id.buttonTicTacToe);
        exitButton = (Button) findViewById(R.id.buttonExit);

        //on click listeners
        anagramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AnagramIntent = new Intent(MainActivity.this, Anagram.class);
                startActivity(AnagramIntent);
            }
        });

        ticTacToeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TicTacToeIntent = new Intent(MainActivity.this, TicTacToe.class);
                startActivity(TicTacToeIntent);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }
}