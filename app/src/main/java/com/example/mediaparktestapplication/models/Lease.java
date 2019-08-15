package com.example.mediaparktestapplication.models;

import com.google.gson.annotations.SerializedName;

public class Lease {

    @SerializedName("workdays")
    private Workdays workdays;
    @SerializedName("weekends")
    private Weekends weekends;
    @SerializedName("kilometerPrice")
    private Float kilometerPrice;
    @SerializedName("freeKilometersPerDay")
    private Integer freeKilometersPerDay;
    @SerializedName("servicePlusBatteryMaxKm")
    private Integer servicePlusBatteryMaxKm;
    @SerializedName("servicePlusBatteryMinKm")
    private Integer servicePlusBatteryMinKm;
    @SerializedName("servicePlusEGoPoints")
    private Integer servicePlusEGoPoints;

    public Lease(Workdays workdays, Weekends weekends, Float kilometerPrice, Integer freeKilometersPerDay, Integer servicePlusBatteryMaxKm, Integer servicePlusBatteryMinKm, Integer servicePlusEGoPoints) {
        this.workdays = workdays;
        this.weekends = weekends;
        this.kilometerPrice = kilometerPrice;
        this.freeKilometersPerDay = freeKilometersPerDay;
        this.servicePlusBatteryMaxKm = servicePlusBatteryMaxKm;
        this.servicePlusBatteryMinKm = servicePlusBatteryMinKm;
        this.servicePlusEGoPoints = servicePlusEGoPoints;
    }

    public Workdays getWorkdays() {
        return workdays;
    }

    public void setWorkdays(Workdays workdays) {
        this.workdays = workdays;
    }

    public Weekends getWeekends() {
        return weekends;
    }

    public void setWeekends(Weekends weekends) {
        this.weekends = weekends;
    }

    public Float getKilometerPrice() {
        return kilometerPrice;
    }

    public void setKilometerPrice(Float kilometerPrice) {
        this.kilometerPrice = kilometerPrice;
    }

    public Integer getFreeKilometersPerDay() {
        return freeKilometersPerDay;
    }

    public void setFreeKilometersPerDay(Integer freeKilometersPerDay) {
        this.freeKilometersPerDay = freeKilometersPerDay;
    }

    public Integer getServicePlusBatteryMaxKm() {
        return servicePlusBatteryMaxKm;
    }

    public void setServicePlusBatteryMaxKm(Integer servicePlusBatteryMaxKm) {
        this.servicePlusBatteryMaxKm = servicePlusBatteryMaxKm;
    }

    public Integer getServicePlusBatteryMinKm() {
        return servicePlusBatteryMinKm;
    }

    public void setServicePlusBatteryMinKm(Integer servicePlusBatteryMinKm) {
        this.servicePlusBatteryMinKm = servicePlusBatteryMinKm;
    }

    public Integer getServicePlusEGoPoints() {
        return servicePlusEGoPoints;
    }

    public void setServicePlusEGoPoints(Integer servicePlusEGoPoints) {
        this.servicePlusEGoPoints = servicePlusEGoPoints;
    }
}
