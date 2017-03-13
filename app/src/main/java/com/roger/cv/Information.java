package com.roger.cv;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by roger on 12/03/17.
 */

public class Information implements Parcelable {
    private final String address;
    private final String birthday;
    private final String cellphone;
    private final String mail;
    private final String name;

    public Information(){
        this.address = "";
        this.birthday = "";
        this.cellphone = "";
        this.mail = "";
        this.name = "";
    }

    public Information(String address, String birthday, String cellphone, String mail, String name){
        this.address = address;
        this.birthday = birthday;
        this.cellphone = cellphone;
        this.mail = mail;
        this.name = name;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeString(this.birthday);
        dest.writeString(this.cellphone);
        dest.writeString(this.mail);
        dest.writeString(this.name);
    }

    protected Information(Parcel in) {
        this.address = in.readString();
        this.birthday = in.readString();
        this.cellphone = in.readString();
        this.mail = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Information> CREATOR = new Parcelable.Creator<Information>() {
        @Override
        public Information createFromParcel(Parcel source) {
            return new Information(source);
        }

        @Override
        public Information[] newArray(int size) {
            return new Information[size];
        }
    };
}
