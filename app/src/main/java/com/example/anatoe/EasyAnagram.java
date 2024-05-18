package com.example.anatoe;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class EasyAnagram extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private String currentWord;
    private String scrambledWord;
    private int currentIndex = 0;
    private int totalPoints = 0;

    private TextView wordTextView, pointsTextView;
    private Button checkButton, nextButton;
    private EditText userGuessEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_anagram);
        dbHelper = new DatabaseHelper(this);
        insertWordsIntoDatabase();

        wordTextView = findViewById(R.id.wordTextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        checkButton = findViewById(R.id.checkButton);
        nextButton = findViewById(R.id.nextButton);
        userGuessEditText = findViewById(R.id.userGuessEditText);

        initializeGame();

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextWord();
            }
        });
    }

    private void insertWordsIntoDatabase() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Add your word list to the database
        String[] wordList = {
                "wonderful", "abstraction",
                "ambiguous", "arithmetic", "backslash",
                "bitmap", "circumstance", "combination",
                "consequently", "gradle", "under",
                "seven", "however", "during",
                "since", "before", "between",
                "about", "resource", "calculate"};

        for (String word : wordList) {
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_WORD, word);
            db.insert(DatabaseHelper.TABLE_NAME, null, values);
        }

        db.close();
    }

    @SuppressLint("Range")
    private void initializeGame() {
        Cursor cursor = dbHelper.getWordList();

        if (currentIndex < cursor.getCount() && cursor.moveToPosition(currentIndex)) {
            currentWord = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_WORD));
            scrambledWord = scrambleWord(currentWord);
            wordTextView.setText(scrambledWord);
            userGuessEditText.setText("");
            updatePointsView();
        } else {
            showToast("Game Over!!");
            checkButton.setEnabled(false);
            nextButton.setEnabled(false);
        }

        cursor.close();
    }

    private void checkAnswer() {
        String userGuess = userGuessEditText.getText().toString();
        if (currentWord.equals(userGuess)) {
            showToast("Correct! +10 points");
            totalPoints += 10;
        } else {
            showToast("Try again! -5 points");
            totalPoints -= 5;
        }

        if (totalPoints <= 0) {
            totalPoints = 0;
            showToast("Total points deducted to zero. Starting again.");
            currentIndex = 0;
            initializeGame(); // Reset the game
        } else {
            updatePointsView();
        }
    }

    @SuppressLint("Range")
    private void nextWord() {
        currentIndex++;
        Cursor cursor = dbHelper.getWordList();
        if (currentIndex < cursor.getCount() && cursor.moveToPosition(currentIndex)) {
            currentWord = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_WORD));
            scrambledWord = scrambleWord(currentWord);
            wordTextView.setText(scrambledWord);
            userGuessEditText.setText("");
            updatePointsView();
        } else {
            showToast("Game Over");
            checkButton.setEnabled(false);
            nextButton.setEnabled(false);
        }

        cursor.close();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void updatePointsView() {
        pointsTextView.setText("Points: " + totalPoints);
    }

    private String scrambleWord(String word) {
        List<String> letters = Arrays.asList(word.split(""));
        Collections.shuffle(letters.subList(1, letters.size() - 1));
        return String.join(" ", letters);
    }
}
