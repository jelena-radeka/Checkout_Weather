package com.example.checkout_weather;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface MyApiEndpointInterface {
    @GET("weather")
    Call<Example> getWeather(@QueryMap Map<String, String> options);
}
