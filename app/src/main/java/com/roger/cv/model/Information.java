package com.roger.cv.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by roger on 12/03/17.
 */
@Entity
public class Information{

    @ColumnInfo
    public String address;
    @ColumnInfo
    public String birthday;
    @ColumnInfo
    public String cellphone;
    @ColumnInfo
    public String imageURL;
    @ColumnInfo
    public String mail;
    @ColumnInfo
    public String name;
    @ColumnInfo
    public String title;

    @PrimaryKey(autoGenerate = true)
    public long uuid;


    public Information(){
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

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }
}
