package com.example.mediaparktestapplication.models;

import com.example.mediaparktestapplication.MainActivity;
import com.example.mediaparktestapplication.adapters.RecyclerAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class Car {

    @SerializedName("id")
    private Integer id;
    @SerializedName("plateNumber")
    private String plateNumber;
    @SerializedName("location")
    private Location location;
    @SerializedName("model")
    private Model model;
    @SerializedName("batteryPercentage")
    private Integer batteryPercentage;
    @SerializedName("batteryEstimatedDistance")
    private Float batteryEstimatedDistance;
    @SerializedName("isCharging")
    private Boolean isCharging;

    public Car(Integer id, String plateNumber, Location location, Model model, Integer batteryPercentage, Float batteryEstimatedDistance, Boolean isCharging) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.location = location;
        this.model = model;
        this.batteryPercentage = batteryPercentage;
        this.batteryEstimatedDistance = batteryEstimatedDistance;
        this.isCharging = isCharging;
    }

    // SORT BY DISTANCE
    public static final Comparator<Car> BY_DISTANCE_ASCENDING = new Comparator<Car>() {
        @Override
        public int compare(Car car, Car t1) {
            return Double.compare(RecyclerAdapter.CalculateDistance(car.getLocation()), RecyclerAdapter.CalculateDistance(t1.getLocation()));
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Integer getBatteryPercentage() {
        return batteryPercentage;
    }

    public void setBatteryPercentage(Integer batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
    }

    public Float getBatteryEstimatedDistance() {
        return batteryEstimatedDistance;
    }

    public void setBatteryEstimatedDistance(Float batteryEstimatedDistance) {
        this.batteryEstimatedDistance = batteryEstimatedDistance;
    }

    public Boolean getCharging() {
        return isCharging;
    }

    public void setCharging(Boolean charging) {
        isCharging = charging;
    }
}