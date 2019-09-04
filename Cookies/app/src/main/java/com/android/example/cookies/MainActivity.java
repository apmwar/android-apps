package com.android.example.cookies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void eatCookie(android.view.View view) {

        TextView tv = (TextView) findViewById(R.id.cookie_text);
        tv.setText("I'm so full...");

        ImageView iv = (ImageView) findViewById(R.id.cookie_pic);
        iv.setImageResource(R.drawable.after);
    }
}
