package com.athaman.basicphrases;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playPhrase(View view){
        String choice =  view.getTag().toString();

        int choiceID= getResources().getIdentifier(choice, "raw", "com.athaman.basicphrases");

        MediaPlayer mPlayer = MediaPlayer.create( this, choiceID );

        mPlayer.start();
    }
}
