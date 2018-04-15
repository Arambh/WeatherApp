package com.example.arambh.weatherapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.constraint.solver.SolverVariable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    Typeface tel;
    TextView textView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Thread splash = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),UI.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        splash.start();
    }
}
