package com.example.sukrit.connect3u;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 -> Yellow
    // 1 -> Red
    // 2 -> Unplayed
    int activePlayer=0;
    boolean gameIsActive=true;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    int testCounter=0;

    public void dropIn(View view) {

//        testCounter++;
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        Log.d("TAG", "dropIn: Counter "+counter.getTag().toString());
        Log.d("TAG", "dropIn: gameState[ "+tappedCounter+"]"+ "="+ gameState[tappedCounter]);


        if (gameState[tappedCounter] == 2  && gameIsActive) {
            testCounter++;

            gameState[tappedCounter]=activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360f).setDuration(100);
        }
        TextView winnerMessage= (TextView) findViewById(R.id.winnerMessage);
        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
        for(int[] winningPosition : winningPositions)
        {
            if(gameState[winningPosition[0]]==gameState[winningPosition[1]]
                    && gameState[winningPosition[1]]==gameState[winningPosition[2]]
                    && gameState[winningPosition[0]]!=2)
            {
//                Toast.makeText(MainActivity.this,gameState[winningPosition[0]]+"",Toast.LENGTH_LONG).show();


                if(gameState[winningPosition[0]]==0) {
                    gameIsActive=false;
                    winnerMessage.setText("Yellow has won..!");
                    layout.setBackgroundColor(Color.parseColor("#ffff00"));
                    testCounter=0;
                }
                else if(gameState[winningPosition[0]]==1)
                {
                    gameIsActive=false;
                    winnerMessage.setText("Red has won..!");
                    layout.setBackgroundColor(Color.parseColor("#ff0000"));
                    testCounter=0;
                }
                else
                {
                }
                layout.setVisibility(View.VISIBLE);
            }
            else if(testCounter==9)
            {
                gameIsActive=false;
                winnerMessage.setText("Match Drawn..!");
                layout.setBackgroundColor(Color.parseColor("#ffffff"));
                layout.setVisibility(View.VISIBLE);
                testCounter=0;
            }
        }

    }

    public void playAgain(View view)
    {
        gameIsActive=true;
        testCounter=0;
        LinearLayout layout= (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer=0;
        //Wrong ---  int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }

        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
