package com.roger.cv.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by roger on 20/03/17.
 */

public class Job {

    private String current;
    private String description;
    private String endDate;
    private String logo;
    private String name;
    private String startDate;
    private String jobPosition;
    private String uuid;

    public Job() {
    }

    public Job(String current, String description, String endDate, String logo, String name, String startDate, String jobPosition) {
        this.current = current;
        this.description = description;
        this.endDate = endDate;
        this.logo = logo;
        this.name = name;
        this.startDate = startDate;
        this.jobPosition = jobPosition;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public void setName(String name) {
        this.name = name;
    }

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


}
