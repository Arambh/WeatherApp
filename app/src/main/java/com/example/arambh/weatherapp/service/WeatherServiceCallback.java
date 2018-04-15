package com.example.arambh.weatherapp.service;

import com.example.arambh.weatherapp.data.Channel;

/**
 * Created by Arambh on 02-Oct-17.
 */

public interface WeatherServiceCallback {

    void serviceSuccess(Channel channel);

    void serviceFailure(Exception exception);
}
