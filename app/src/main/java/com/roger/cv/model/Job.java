package com.roger.cv.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by roger on 20/03/17.
 */

@Entity
public class Job {

    @ColumnInfo
    public String current;
    @ColumnInfo
    public String description;
    @ColumnInfo
    public String endDate;
    @ColumnInfo
    public String logo;
    @ColumnInfo
    public String name;
    @ColumnInfo
    public String startDate;
    @ColumnInfo
    public String jobPosition;
    @PrimaryKey(autoGenerate = true)
    private long uuid;

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

    public boolean getCurrent() {
        return Boolean.parseBoolean(this.current);
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }
}
