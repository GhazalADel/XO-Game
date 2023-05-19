package com.example.peyman.xo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public boolean turnYellow = false; //false red true yellow
    public int[] positionState = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    public int[] redPositions = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    public int[] yellowPositions = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[][] winningPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6},
            {2, 5, 8}, {1, 4, 7}, {0, 4, 8}, {2, 4, 6}};
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.winnerMessage);
    }

    public void dropIn(View view) {
        ImageView currentImageView = (ImageView) view;
        int tag = Integer.parseInt(String.valueOf(currentImageView.getTag()));
        if (positionState[tag] == 0) {
            positionState[tag] = 1;
            if (turnYellow) {
                yellowPositions[tag] = 1;
                currentImageView.setImageResource(R.drawable.yellow);
                turnYellow = false;
                if (isWinner(yellowPositions)) {
                    textView.setText("Yellow just Won the game!!!");
                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    linearLayout.setVisibility(View.VISIBLE);
                }

            } else {
                redPositions[tag] = 1;
                currentImageView.setImageResource(R.drawable.red);
                turnYellow = true;
                if (isWinner(redPositions)) {
                    textView.setText("Red just Won the game!!!");
                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    private boolean isWinner(int[] positions) {
        int cnt = 0;
        for (int i = 0; i < winningPosition.length; i++) {
            for (int j = 0; j < winningPosition[i].length; j++) {
                int index = winningPosition[i][j];
                if (positions[index] == 1) {
                    cnt++;
                }
            }
            if (cnt == 3)
                return true;
            else
                cnt = 0;
        }
        return false;
    }

    public void resetGame() {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }
    }

    public void playAgain(View view) {
        resetGame();
        turnYellow = false; //false red true yellow
        Arrays.fill(positionState,0);
        Arrays.fill(redPositions,0);
        Arrays.fill(yellowPositions,0);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
        linearLayout.setVisibility(View.INVISIBLE);
    }
}
