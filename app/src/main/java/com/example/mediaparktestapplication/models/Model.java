package com.example.mediaparktestapplication.models;

import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("photoUrl")
    private String photoUrl;
    @SerializedName("rate")
    private Rate rate;

    public Model(Integer id, String title, String photoUrl, Rate rate) {
        this.id = id;
        this.title = title;
        this.photoUrl = photoUrl;
        this.rate = rate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }
}
