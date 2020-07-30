package com.roger.cv.model;

public class Study {
    String degree;
    String degreeTitle;
    String startDate;
    String endDate;

    public Study(String degree, String degreeTitle, String startDate, String endDate) {
        this.degree = degree;
        this.degreeTitle = degreeTitle;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Study() {
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
