package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view){
        if(quantity<100){
            quantity = quantity + 1;
            displayQuantity(quantity);
        }else{
            Toast.makeText(MainActivity.this, getResources().getString(R.string.over_100), Toast.LENGTH_SHORT).show();
        }
    }

    public void decrement(View view){
        if(quantity>1){
        quantity = quantity - 1;
        displayQuantity(quantity);
        }else{
            Toast.makeText(MainActivity.this, getResources().getString(R.string.under_1), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // grab the check box values and store them as booleans for chocolate and whipped cream
        CheckBox whippedCream = (CheckBox) findViewById(R.id.Whipped_Cream_Checkbox);
        boolean hasWhippedCream = whippedCream.isChecked();
        CheckBox chocolate = (CheckBox) findViewById(R.id.Chocolate_Checkbox);
        boolean hasChocolate = chocolate.isChecked();

        //grab the user input for the name entry
        EditText userName = (EditText) findViewById(R.id.User_Name);
        Editable name = userName.getText();

        //set up the variables for the order
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(quantity, price, hasWhippedCream, hasChocolate, name);

        //create an intent to email the order
        Intent submitOrder = new Intent(Intent.ACTION_SENDTO);
        submitOrder.setData(Uri.parse("mailto:"));
        submitOrder.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.subject)+ name);
        submitOrder.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if(submitOrder.resolveActivity(getPackageManager())!=null){
            startActivity(submitOrder);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }



    /**
     * calculate price of an order and return it as int.
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate){
        int basePrice = 5;
        if(hasWhippedCream){
            basePrice = basePrice + 1;
        }
        if(hasChocolate){
            basePrice = basePrice + 2;
        }

        int price = basePrice * quantity;

        return price;
    }

    private String createOrderSummary(int quantity, int price, boolean hasWhippedCream, boolean hasChocolate, Editable name){
        return getResources().getString(R.string.name) + name
                + getResources().getString(R.string.with_cream) + hasWhippedCream
                + getResources().getString(R.string.with_chocolate) + hasChocolate
                + getResources().getString(R.string.quantity_out) + quantity
                + getResources().getString(R.string.all_up) + price
                + getResources().getString(R.string.thanks);
    }

}