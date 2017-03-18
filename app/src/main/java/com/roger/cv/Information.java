package com.roger.cv;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by roger on 12/03/17.
 */

public class Information implements Parcelable {
    private final String address;
    private final String birthday;
    private final String cellphone;
    private final String imageURL;
    private final String mail;
    private final String name;
    private final String title;


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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeString(this.birthday);
        dest.writeString(this.cellphone);
        dest.writeString(this.imageURL);
        dest.writeString(this.mail);
        dest.writeString(this.name);
        dest.writeString(this.title);
    }

    protected Information(Parcel in) {
        this.address = in.readString();
        this.birthday = in.readString();
        this.cellphone = in.readString();
        this.imageURL = in.readString();
        this.mail = in.readString();
        this.name = in.readString();
        this.title = in.readString();
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
