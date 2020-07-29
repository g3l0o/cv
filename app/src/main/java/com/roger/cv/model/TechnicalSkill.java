package com.roger.cv.model;

import java.util.ArrayList;

public class TechnicalSkill {

    public String technology;
    public String logo;
    public ArrayList<String> skills;

    public TechnicalSkill() {
    }

    public TechnicalSkill(String technology, String logo, ArrayList<String> skills) {
        this.technology = technology;
        this.logo = logo;
        this.skills = skills;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }
}
