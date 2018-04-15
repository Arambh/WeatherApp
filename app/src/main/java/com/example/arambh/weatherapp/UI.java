package com.example.arambh.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UI extends AppCompatActivity {

    public String city,state,country,loc;
    EditText t1;
    EditText t2;
    EditText t3;
    Button b1;
    Button b2;
    Button b3;
    //TextView textView3;
    //Typeface tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);

        t1 = (EditText) findViewById(R.id.t1);
        t2 = (EditText) findViewById(R.id.t2);
        t3 = (EditText) findViewById(R.id.t3);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        /*textView3= (TextView) findViewById(R.id.textView3);
        tel= Typeface.createFromAsset(getAssets(), "tele.TTF");
        textView3.setTypeface(tel);*/



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*city= t1.getText().toString();
                state= String.valueOf(t2.getText());
                country= String.valueOf(t3.getText());*/
                /*city="Bhubaneswar";
                state="OD";
                country="IND";*/
                city= String.valueOf(t1.getText());
                state= String.valueOf(t2.getText());
                country= String.valueOf(t3.getText());
                loc=city+", "+state+", "+country;

                Intent intent = new Intent(UI.this,WeatherActivity.class);
                intent.putExtra("loc",loc);
                startActivity(intent);
                //startActivity(new Intent(getApplicationContext(),WeatherActivity.class));
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Currentloc.class));
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setText("");
                t2.setText("");
                t3.setText("");
            }
        });
    }
}
