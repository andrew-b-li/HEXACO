package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/*
 * Represents a user with a name and list of assessments
 */
public class User {
    private String userName;
    private ArrayList<Assessment> assessmentList = new ArrayList<>();

    //Construct a User
    //Effects: Assigns User a name and list to hold assessments
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

    public ArrayList<Assessment> getAssessmentList() {
        return assessmentList;
    }

    //Modifies: this
    //Effects: adds an Assessment to assessmentList
    public void addAssessment(Assessment assessment) {
        this.assessmentList.add(assessment);
    }

    //Effects: Returns a String with the user's name and assessments
    @Override
    public String toString() {
        String list = this.getUserName() + ": ";
        if (!assessmentList.isEmpty()) {
            if (assessmentList.size() == 1) {
                Assessment assessment = assessmentList.get(0);
                list = list + assessment.toString();
            } else {
                for (Assessment assessment : assessmentList) {
                    list = list + assessment.toString() + ", ";
                }
                list = list.substring(0, list.length() - 2);
            }
        } else {
            list = list.substring(0, list.length() - 1);
        }
        return list;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("User Name", userName);
        json.put("Assessments", assessmentsToJson());
        return json;
    }

    private JSONArray assessmentsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Assessment assessment : assessmentList) {
            jsonArray.put(assessment.toJson());
        }
        return jsonArray;
    }

    //Effects: Clears assessmentList
    public void clearAssessments() {
        assessmentList.clear();
    }

}
