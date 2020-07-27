package com.roger.cv.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by roger on 12/03/17.
 */

public class Information{
    private final String address;
    private final String birthday;
    private final String cellphone;
    private final String imageURL;
    private final String mail;
    private final String name;
    private final String title;
    private int uuid;


    public Information(){
        this.address = "";
        this.birthday = "";
        this.cellphone = "";
        this.imageURL = "";
        this.mail = "";
        this.name = "";
        this.title = "";
    }

    public Information(String address, String birthday, String cellphone, String imageURL, String mail, String name, String title){
        this.address = address;
        this.birthday = birthday;
        this.cellphone = cellphone;
        this.imageURL = imageURL;
        this.mail = mail;
        this.name = name;
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public String getImageURL(){ return imageURL;}

    public String getTitle(){ return title;}


}
