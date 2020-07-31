package com.roger.cv.model;

public class Certificate {
    String date;
    String logo;
    String name;
    String provider;

    public Certificate() {
    }

    public Certificate(String date, String logo, String name, String provider) {
        this.date = date;
        this.logo = logo;
        this.name = name;
        this.provider = provider;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
