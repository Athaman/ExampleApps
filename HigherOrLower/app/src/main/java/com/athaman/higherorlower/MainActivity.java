package com.athaman.higherorlower;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int random;
    Random generator = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        random = generator.nextInt(20) + 1;
    }

    public void guess(View view){
        EditText inputField = (EditText) findViewById(R.id.inputField);
        Integer input = 0;
        if(inputField.getText().length()== 0) {
            Toast.makeText(getApplicationContext(), "Please Enter A Number", Toast.LENGTH_SHORT).show();
        }else{
            input = Integer.parseInt(inputField.getText().toString());
        }
        String result;
        if(input > random){
            result = "Too High!";
        }else if(input < random){
            result = "Too Low!";
        }else{
            result = "CONGRATULATIONS, Try again";
            random = generator.nextInt(20)+1;
        }

        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

    }
}
