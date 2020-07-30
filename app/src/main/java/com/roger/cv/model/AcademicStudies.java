package com.roger.cv.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    private String getDateRange(String startDateString, String endDateString){
        String showDatePattern = "MMMM yyyy";
        String fireBaseDatePattern = "yyyy/MM/dd";
        String showDateSeparator = " - ";

        StringBuilder dateRange = new StringBuilder();
        DateFormat dateFormat = new SimpleDateFormat(showDatePattern);

        try {
            Date startDate = new SimpleDateFormat(fireBaseDatePattern).parse(startDateString);
            dateRange.append("(").append(dateFormat.format(startDate)).append(showDateSeparator);

            Date endDate = new SimpleDateFormat(fireBaseDatePattern).parse(endDateString);
            dateRange.append(dateFormat.format(endDate)).append(")");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateRange.toString();

    }

    public String getStudiesText(){

        String bulletIcon = "✓ ";
        StringBuilder builder = new StringBuilder();

        for (Study s: studies) {
            builder.append(bulletIcon).append(s.degree).append(" - ").append("<b>").append(s.degreeTitle).append("</b>").append("<br>")
                    .append("\t\t\t").append(getDateRange(s.startDate, s.endDate)).append("<br>");
        }

        return builder.toString();
    }

}
