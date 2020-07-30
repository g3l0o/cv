package com.roger.cv.model;

import java.util.ArrayList;

public class AcademicStudies {

    public String logo;
    public String place;
    public String school;
    public ArrayList<Studies> studies;

    public AcademicStudies() {
    }

    public AcademicStudies(String logo, String place, String school, ArrayList<Studies> studies) {
        this.logo = logo;
        this.place = place;
        this.school = school;
        this.studies = studies;
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

    public ArrayList<Studies> getStudies() {
        return studies;
    }

    public void setStudies(ArrayList<Studies> studies) {
        this.studies = studies;
    }

    private class Studies{
        String degree;
        String degreeTitle;
        String startDate;
        String endDate;

        public Studies(String degree, String degreeTitle, String startDate, String endDate) {
            this.degree = degree;
            this.degreeTitle = degreeTitle;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public Studies() {
        }

        public String getDegree() {
            return degree;
        }

        public void setDegree(String degree) {
            this.degree = degree;
        }

        public String getDegreeTitle() {
            return degreeTitle;
        }

        public void setDegreeTitle(String degreeTitle) {
            this.degreeTitle = degreeTitle;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }
    }
}
