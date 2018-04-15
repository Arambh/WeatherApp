package com.example.arambh.weatherapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arambh.weatherapp.data.Channel;
import com.example.arambh.weatherapp.data.Item;
import com.example.arambh.weatherapp.service.WeatherServiceCallback;
import com.example.arambh.weatherapp.service.YahooWeatherService;

@SuppressWarnings("ALL")
public class WeatherActivity extends AppCompatActivity implements WeatherServiceCallback {

    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;
    private Button btncustom;
    private Button btncurr;
    private RadioGroup rgrp;
    private RadioButton r;

    private YahooWeatherService service;
    private ProgressDialog dialog;
    public String loc;
    int temp;
   // public int rid;

    public WeatherActivity() {
    }


    /*public void data(String city, String state, String country)
    {
        this.city=city;
        this.state=state;
        this.country=country;
    }*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weatherIconImageView = (ImageView) findViewById(R.id.weatherIconImageView);
        temperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
        conditionTextView = (TextView) findViewById(R.id.conditionTextView);
        locationTextView = (TextView) findViewById(R.id.locationTextView);
        btncustom = (Button) findViewById(R.id.btncustom);
        btncurr = (Button) findViewById(R.id.btncurr);
        rgrp= (RadioGroup) findViewById(R.id.rgrp);

        Intent intent=getIntent();
        loc=intent.getStringExtra("loc");

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        service.refreshWeather(loc);
        //service.refreshWeather(city+", "+state+", "+country);
       // service.refreshWeather("Bhubaneswar, OD, IND");

        btncustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),UI.class));
            }
        });

        btncurr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Currentloc.class));
            }
        });

    }

    public void rbclick(View v)
    {

       int rid = rgrp.getCheckedRadioButtonId();
        r= (RadioButton) findViewById(rid);

        if((r.getText()).equals("Celsius"))
        {
            Toast.makeText(getBaseContext(),temperatureTextView.getText(),Toast.LENGTH_LONG).show();
        }
        if ((r.getText()).equals("Fahrenheit"))
        {
            Toast.makeText(getBaseContext(),String.valueOf(temp*1.8+32)+"\u00B0"+"F",Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();
        Item item = channel.getItem();
        int resourceId = getResources().getIdentifier("drawable/icon_"+item.getCondition().getCode(),null,getPackageName());
        Drawable weatherIconDrawable = getResources().getDrawable(resourceId);
        weatherIconImageView.setImageDrawable(weatherIconDrawable);
        temperatureTextView.setText(item.getCondition().getTemperature()+"\u00B0"+channel.getUnits().getTemperature());
        temp=item.getCondition().getTemperature();
        conditionTextView.setText(item.getCondition().getDescription());
        locationTextView.setText(service.getLocation());
       /* if((r.getText()).equals("Celsius"))
        {
            temperatureTextView.setText(item.getCondition().getTemperature()+"\u00B0"+channel.getUnits().getTemperature());
        }
        if ((r.getText()).equals("Fahrenheit"))
        {
            temperatureTextView.setText(item.getCondition().getTemperature()+"\u00B0");
        }*/

    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();


    }
}
