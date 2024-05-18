package com.example.anatoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class TicTacToe extends AppCompatActivity {
    boolean gameActive = true;
    // 0 - X
    // 1 - O
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    // State meanings:
    //    0 - X
    //    1 - O
    //    2 - Null
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // win positions
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    public static int counter = 0;
    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (!gameActive)  // game reset function (called if someone wins or the boxes are full)
        {
            gameReset(view);
            counter = 0; //Reset the counter
        }
        // if the tapped image is empty
        if (gameState[tappedImage] == 2) {
            // increase the counter
            // after every tap
            counter++;

            // check if its the last box
            if (counter == 9) {
                // reset the game
                gameActive = false;
            }

            // mark this position
            gameState[tappedImage] = activePlayer;

            // this will give a motion
            // effect to the image
            img.setTranslationY(-1000f);

            // change the active player
            // from 0 to 1 or 1 to 0
            if (activePlayer == 0) {
                // set the image of x
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);

                // change the status
                status.setText("Player O's Turn");
            } else {
                // set the image of o
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);

                // change the status
                status.setText("Player X's Turn");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        int flag = 0;
        // Check if any player has won if counter is > 4 as min 5 taps are
        // required to declare a winner
        if (counter > 4) {
            for (int[] winPosition : winPositions) {
                if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                        gameState[winPosition[1]] == gameState[winPosition[2]] &&
                        gameState[winPosition[0]] != 2) {
                    flag = 1;

                    // Somebody has won! - Find out who!
                    String winnerStr;

                    // game reset function be called
                    gameActive = false;
                    if (gameState[winPosition[0]] == 0) {
                        winnerStr = "Player X WINS!";
                    } else {
                        winnerStr = "Player O WINS!";
                    }
                    // Update the status bar for winner announcement
                    TextView status = findViewById(R.id.status);
                    status.setText(winnerStr);
                }
            }
            // set the status if the match draw
            if (counter == 9 && flag == 0) {
                TextView status = findViewById(R.id.status);
                status.setText("It's a Draw");
            }
        }
    }

    // reset the game
    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        //set all position to Null
        Arrays.fill(gameState, 2);
        // remove all the images from the boxes inside the grid
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");
    }
    Button restart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        restart = (Button) findViewById(R.id.buttonRestart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = null;
                gameReset(view);
            }
        });
    }
}

