package com.athaman.shapenumbers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkShapes(View view){
        Integer input;
        String message;
        EditText inputField = (EditText) findViewById(R.id.inputField);
        if(inputField.getText().length() != 0) {
            input = Integer.parseInt(inputField.getText().toString());
            boolean square = isSquare(input);
            boolean triangle = isTriangle(input);
            if(square && triangle){
                message = "This number is both square and triangular";
            }else if(square){
                message = "This number is square but not triangular";
            }else if(triangle){
                message = "This number is triangular but not square";
            }else{
                message = "This number is neither square nor triangular";
            }
        }else{
            message = "Please input a number";
        }


        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    public boolean isTriangle(Integer input){
        boolean result = false;
        int x = 1;
        int triangle = 1;

        while (triangle < input){
            x++;
            triangle += x;
        }

        if(triangle == input){
            result = true;
        }
        return result;
    }

    public boolean isSquare(Integer input){
        boolean result = false;
        double inputRoot = Math.sqrt((double) input);

        if(inputRoot == Math.floor(inputRoot)){
            result = true;
        }

        return result;
    }
}
