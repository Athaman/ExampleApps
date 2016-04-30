package com.athaman.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CurrencyConverter extends AppCompatActivity {

    public void convert(View view){

        EditText inputField = (EditText) findViewById(R.id.inputField);

        TextView outputField = (TextView) findViewById(R.id.outputField);

        Double input = Double.parseDouble(inputField.getText().toString());
        // convert the currency, in this example we use AUD to Euro
        Double output = input * .7;
        outputField.setText(output.toString());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
