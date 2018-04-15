package com.example.arambh.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;
import java.util.jar.Manifest;

public class Currentloc extends AppCompatActivity {

    Button button;
    Button button2;
    Button blink;
    TextView textView;
    String loc;
    LocationManager lmn;
    Location ln;

    private static final int MY_PERMISSION_REQUEST_LOCATION=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentloc);

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.textView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                locate();
            }

            public void locate(){
                if (ContextCompat.checkSelfPermission(Currentloc.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    //we dont have permission
                    ActivityCompat.requestPermissions(Currentloc.this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);
                }
                else
                {
                    //lmn = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    lmn = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    ln = lmn.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                    try
                    {
                        textView.setText(hereLocation(ln.getLatitude(), ln.getLongitude()));
                        loc = hereLocation(ln.getLatitude(), ln.getLongitude());

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        Toast.makeText(Currentloc.this, "Not found : "+e, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Currentloc.this,WeatherActivity.class);
                intent.putExtra("loc",loc);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSION_REQUEST_LOCATION: {
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(Currentloc.this,"Permission granted",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Currentloc.this,"Permission denied",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public String hereLocation(double lat, double lon) {

        String city = "",country="";
        Geocoder geocoder = new Geocoder(Currentloc.this, Locale.getDefault());
        List<Address> addressList;
        try {
            addressList= geocoder.getFromLocation(lat,lon,1);
            if(addressList.size()>0){

                city=addressList.get(0).getLocality();
                country=addressList.get(0).getCountryName();

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return city+", "+country;
    }
}
