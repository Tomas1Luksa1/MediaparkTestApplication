package com.example.mediaparktestapplication.models;

import com.google.gson.annotations.SerializedName;

public class Workdays {

    @SerializedName("amount")
    private Float amount;
    @SerializedName("minutes")
    private Integer minutes;
    @SerializedName("dailyAmount")
    private Float dailyAmount;
    @SerializedName("minimumPrice")
    private Float minimumPrice;
    @SerializedName("minimumMinutes")
    private Integer minimumMinutes;

    public Workdays(Float amount, Integer minutes, Float dailyAmount, Float minimumPrice, Integer minimumMinutes) {
        this.amount = amount;
        this.minutes = minutes;
        this.dailyAmount = dailyAmount;
        this.minimumPrice = minimumPrice;
        this.minimumMinutes = minimumMinutes;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Float getDailyAmount() {
        return dailyAmount;
    }

    public void setDailyAmount(Float dailyAmount) {
        this.dailyAmount = dailyAmount;
    }

    public Float getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(Float minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public Integer getMinimumMinutes() {
        return minimumMinutes;
    }

    public void setMinimumMinutes(Integer minimumMinutes) {
        this.minimumMinutes = minimumMinutes;
    }
}
