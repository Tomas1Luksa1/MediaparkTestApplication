package com.example.mediaparktestapplication.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mediaparktestapplication.models.Car;
import com.example.mediaparktestapplication.repositories.CarRepository;

import java.util.List;

public class CarViewModel extends ViewModel {

    private LiveData<List<Car>> mCars;
    private CarRepository mRepo;

    public void init(){
        if (mCars != null) {
            return;
        }
        mRepo = CarRepository.getInstance();
        mCars = mRepo.getCars();
    }

    public LiveData<List<Car>> getCars(){
        return mCars;
    }
}
