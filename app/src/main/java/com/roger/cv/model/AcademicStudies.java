package com.roger.cv.model;

import java.util.ArrayList;

public class AcademicStudies {

    public String logo;
    public String place;
    public String school;
    public ArrayList<Study> studies = new ArrayList<Study>();

    public AcademicStudies() {
    }

    public AcademicStudies(String logo, String place, String school, ArrayList<Study> studies) {
        this.logo = logo;
        this.place = place;
        this.school = school;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

}
