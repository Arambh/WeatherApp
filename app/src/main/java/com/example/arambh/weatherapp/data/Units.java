package com.example.arambh.weatherapp.data;

import org.json.JSONObject;

/**
 * Created by Arambh on 02-Oct-17.
 */

public class Units implements JSONPopulator {
    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.optString("temperature");


    }
}
