package model;

import java.util.ArrayList;

public class User {
    private String userName;
    private ArrayList<Assessment> assessments = new ArrayList<>();

    public User(String name) {
        this.userName = name;
        ArrayList<Assessment> assessments = new ArrayList<>();
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getUserName() {
        return userName;
    }

    public void addAssessment(Assessment assessment) {
        this.assessments.add(assessment);
    }

    public ArrayList<Assessment> getAssessments() {
        return assessments;
    }
}
