package com.example.mediaparktestapplication.models;

import com.google.gson.annotations.SerializedName;

public class Reservation {

    @SerializedName("initialPrice")
    private Float initialPrice;
    @SerializedName("initialMinutes")
    private Integer initialMinutes;
    @SerializedName("extensionPrice")
    private Float extensionPrice;
    @SerializedName("extensionMinutes")
    private Integer extensionMinutes;
    @SerializedName("longerExtensionPrice")
    private Float longerExtensionPrice;
    @SerializedName("longerExtensionMinutes")
    private Integer longerExtensionMinutes;

    public Reservation(Float initialPrice, Integer initialMinutes, Float extensionPrice, Integer extensionMinutes, Float longerExtensionPrice, Integer longerExtensionMinutes) {
        this.initialPrice = initialPrice;
        this.initialMinutes = initialMinutes;
        this.extensionPrice = extensionPrice;
        this.extensionMinutes = extensionMinutes;
        this.longerExtensionPrice = longerExtensionPrice;
        this.longerExtensionMinutes = longerExtensionMinutes;
    }

    public Float getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(Float initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Integer getInitialMinutes() {
        return initialMinutes;
    }

    public void setInitialMinutes(Integer initialMinutes) {
        this.initialMinutes = initialMinutes;
    }

    public Float getExtensionPrice() {
        return extensionPrice;
    }

    public void setExtensionPrice(Float extensionPrice) {
        this.extensionPrice = extensionPrice;
    }

    public Integer getExtensionMinutes() {
        return extensionMinutes;
    }

    public void setExtensionMinutes(Integer extensionMinutes) {
        this.extensionMinutes = extensionMinutes;
    }

    public Float getLongerExtensionPrice() {
        return longerExtensionPrice;
    }

    public void setLongerExtensionPrice(Float longerExtensionPrice) {
        this.longerExtensionPrice = longerExtensionPrice;
    }

    public Integer getLongerExtensionMinutes() {
        return longerExtensionMinutes;
    }

    public void setLongerExtensionMinutes(Integer longerExtensionMinutes) {
        this.longerExtensionMinutes = longerExtensionMinutes;
    }
}
