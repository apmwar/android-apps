package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;
import android.content.Intent;

//This app displays an order form to order coffee.
public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    double price = 40.00;
    boolean hasOrdered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void submitOrder(View view) {
        TextView status = (TextView) findViewById(R.id.order_summary);
        hasOrdered = true;
        CheckBox cb = (CheckBox) findViewById(R.id.has_whipped_cream);
        CheckBox cho = (CheckBox) findViewById(R.id.has_chocolate);
        EditText name = (EditText) findViewById(R.id.name);

        String message = "Name: " + name.getText() +"\n";
        message += "Quantity: " + quantity + "\n";

        if(cb.isChecked()) {
            message += "Add Whipped Cream? True\n";
        } else {
            message += "Add Whipped Cream? False\n";
        }
        if(cho.isChecked()) {
            message += "Add Chocolate? True\n";
        } else {
            message += "Add Chocolate? False\n";
        }

        message += "Total: " + NumberFormat.getCurrencyInstance().format(price) + "\n\n";
        message += "Thank you. Order coming right up!";

        String subject = "Just Java order for " + name.getText();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(android.net.Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
        }
    }

    public void addTopping(View view) {
        displayPrice();
    }

    //This method will increase number of cups of coffee
    public void increaseOrder(View view) {
        if (!hasOrdered) {
            TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
            quantity++;
            quantityTextView.setText("" + quantity);
            displayPrice();

        }
    }

    //This method will decrease number of cups of coffee
    public void decreaseOrder(View view) {
        if (!hasOrdered) {
            TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
            if (quantity > 1) {
                quantity--;
            }
            quantityTextView.setText("" + quantity);
            displayPrice();
        }
    }

    //This method will display total cost of coffee
    //@param n is the total cost of the coffee and should be displayed
    private void displayPrice() {
        CheckBox cb = (CheckBox) findViewById(R.id.has_whipped_cream);
        CheckBox cho = (CheckBox) findViewById(R.id.has_chocolate);

        //standard rate is 40rs per cup
        //adding whipped cream is 15rs
        //adding chocolate is 10rs

        if(cb.isChecked() && cho.isChecked()) {
            price = (quantity * 65);
        } else if(cb.isChecked()){
            price = (quantity * 55);
        } else if(cho.isChecked()) {
            price = (quantity * 50);
        } else {
            price = (quantity * 40);
        }
        TextView priceTextView = (TextView) findViewById(R.id.order_summary);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(price));

    }

    public void showMap(android.net.Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}