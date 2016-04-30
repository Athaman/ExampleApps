package com.athaman.tictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    int[] squareOwner = {2,2,2,2,2,2,2,2,2};
    int[][] winningSequences = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,5},{2,5,8},{0,4,8},{2,4,6}};
    int playerTurn=0;
    int playAgain=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void move(View view){
        ImageView square = (ImageView) view;
        square.setClickable(false);
        int squareLocation = Integer.parseInt(square.getTag().toString());
        squareOwner[squareLocation] = playerTurn;
        if(playerTurn == 0){
            square.setImageResource(R.drawable.cid);
            playerTurn= 1;
        }else{
            square.setImageResource(R.drawable.cloud);
            playerTurn = 0;
        }
        checkForVictory();
    }

    public void checkForVictory(){
        for(int[] sequence: winningSequences){
            if(squareOwner[sequence[0]] != 2 &&
                    squareOwner[sequence[0]] == squareOwner[sequence[1]] &&
                    squareOwner[sequence[1]] == squareOwner[sequence[2]]){


                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                dialogBuilder.setMessage("We have a winner, play again?");
                dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        recreate();
                    }
                });
                dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog replayDialog = dialogBuilder.create();
                replayDialog.show();



            }
        }
    }
}
