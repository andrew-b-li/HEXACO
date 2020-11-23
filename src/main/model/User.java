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
    private Team team;

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

    public Team getTeam() {
        return  team;
    }

    public ArrayList<Assessment> getAssessmentList() {
        return assessmentList;
    }

    //Modifies: this
    //Effects: adds an Assessment to assessmentList
    public void addAssessment(Assessment assessment) {
        this.assessmentList.add(assessment);
    }

    //Modifies: this
    //Effects: adds a Team to the User
    public void addTeam(Team team) {
        if (this.team != null) {
            if (!this.team.equals(team)) {
                this.team.removeUser(this);
                this.team = null;
            }
        }
        this.team = team;
        team.addUser(this);
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
                    list = list + assessment.toString() + "\n";
                }
                list = list.substring(0, list.length() - 1);
            }
        } else {
            list = list.substring(0, list.length() - 1);
        }
        return list;
    }

    //Effects: Calls assessmentsToJson to return a list of Assessments
    //associated with a User as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("User Name", userName);
        json.put("Assessments", assessmentsToJson());
        return json;
    }

    //Effects: Iterates through the Assessments in assessmentList and returns the information as a JSONArray
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return userName != null ? userName.equals(user.userName) : user.userName == null;
    }

    @Override
    public int hashCode() {
        return userName != null ? userName.hashCode() : 0;
    }
}
