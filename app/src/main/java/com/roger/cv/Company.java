package com.roger.cv;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by roger on 20/03/17.
 */

public class Company implements Parcelable {
    private String current;
    private String description;
    private String endDate;
    private String logo;
    private String name;
    private String startDate;

    public String getCurrent() {
        return current;
    }

    public String getDescription() {
        return description;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getLogo() {
        return logo;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.current);
        dest.writeString(this.description);
        dest.writeString(this.endDate);
        dest.writeString(this.logo);
        dest.writeString(this.name);
        dest.writeString(this.startDate);
    }

    public Company() {
    }

    protected Company(Parcel in) {
        this.current = in.readString();
        this.description = in.readString();
        this.endDate = in.readString();
        this.logo = in.readString();
        this.name = in.readString();
        this.startDate = in.readString();
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel source) {
            return new Company(source);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };
}
