package com.roger.cv.model;

import android.content.res.Resources;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public String getExpeditedDate() {
        String expedited = "Expedición: ";
        String showDatePattern = "MMMM yyyy";
        String fireBaseDatePattern = "yyyy/MM/dd";

        StringBuilder expeditedDate = new StringBuilder();
        DateFormat dateFormat = new SimpleDateFormat(showDatePattern);

        try {
            Date date = new SimpleDateFormat(fireBaseDatePattern).parse(getDate());
            expeditedDate.append(expedited).append(dateFormat.format(date));
        }catch (ParseException e){
            e.printStackTrace();
        }
        return expeditedDate.toString();
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
