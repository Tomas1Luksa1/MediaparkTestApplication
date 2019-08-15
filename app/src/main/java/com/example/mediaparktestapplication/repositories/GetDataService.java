package com.example.mediaparktestapplication.repositories;

import com.example.mediaparktestapplication.models.Car;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("api/mobile/public/availablecars")
    Call<List<Car>> getAvailableCars();
}
