package com.example.mediaparktestapplication.models;

import com.google.gson.annotations.SerializedName;

public class Rate {

    @SerializedName("isWeekend")
    private Boolean isWeekend;
    @SerializedName("currency")
    private String currency;
    @SerializedName("currencySymbol")
    private String currencySymbol;
    @SerializedName("lease")
    private Lease lease;
    @SerializedName("reservation")
    private Reservation reservation;

    public Rate(Boolean isWeekend, String currency, String currencySymbol, Lease lease, Reservation reservation) {
        this.isWeekend = isWeekend;
        this.currency = currency;
        this.currencySymbol = currencySymbol;
        this.lease = lease;
        this.reservation = reservation;
    }

    public Boolean getWeekend() {
        return isWeekend;
    }

    public void setWeekend(Boolean weekend) {
        isWeekend = weekend;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public Lease getLease() {
        return lease;
    }

    public void setLease(Lease lease) {
        this.lease = lease;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
