package com.roger.cv.model;

public class Course {

    String logo;
    String course;
    String provider;

    public Course() {
    }

    public Course(String logo, String course, String provider) {
        this.logo = logo;
        this.course = course;
        this.provider = provider;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
