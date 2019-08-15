package com.example.mediaparktestapplication.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.mediaparktestapplication.models.Car;

import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarRepository {

    private static final String BASE_URL = "https://development.espark.lt/";
    private GetDataService getDataService;
    private static CarRepository instance;

    private CarRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getDataService = retrofit.create(GetDataService.class);
    }

    public static CarRepository getInstance(){
        if (instance == null){
            instance = new CarRepository();
        }
        return instance;
    }

    public LiveData<List<Car>> getCars(){
        final MutableLiveData<List<Car>> data = new MutableLiveData<>();
        getDataService.getAvailableCars().enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                List<Car> cars = response.body();
                Collections.sort(cars, Car.BY_DISTANCE_ASCENDING);
                data.setValue(cars);
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                // TODO Proper error handling
                data.setValue(null);
            }
        });
        return data;
    }
}
