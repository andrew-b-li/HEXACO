package persistence;

import model.App;
import model.Assessment;
import model.Team;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//Represents a reader that reads App from JSON data stored in file
//This class is based on the CPSC 210 Json Serialization Demo:
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private String source;

    //Effects: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //Effects: reads App from file and returns it
    //throws IOException if an error occurs reading data from file
    public App read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseApp(jsonObject);
    }

    //Effects: reads source file as string and returns it
    //throws IOException if an error occurs reading data from file
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    //Effects: Parses App from JSON object and returns it
    private App parseApp(JSONObject jsonObjet) {
        App yourApp = new App();
        addData(yourApp, jsonObjet);
        return yourApp;
    }

    //Modifies: yourApp
    //Effects: Parses team data from JSON object and adds the teams to yourApp
    private void addData(App yourApp, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Teams");
        for (Object json : jsonArray) {
            JSONObject nextTeam = (JSONObject) json;
            Team teamWithData = getTeamInfo(nextTeam);
            yourApp.addTeam(teamWithData);
        }
    }

    //Effects: Parses User data for a Team from JSON object, adds the Users
    // to the Team, and returns the Team
    private Team getTeamInfo(JSONObject jsonObject) {
        String name = jsonObject.getString("Team Name");
        Team newTeam = new Team(name);
        JSONArray jsonArray = jsonObject.getJSONArray("Users");
        for (Object json : jsonArray) {
            JSONObject nextUser = (JSONObject) json;
            User newUser = getUserInfo(nextUser);
            newTeam.addUser(newUser);
        }
        return newTeam;
    }

    //Effects: Parses Assessment data for a user from JSON object, adds the Assessments
    //to the User, and returns the User
    private User getUserInfo(JSONObject jsonObject) {
        String name = jsonObject.getString("User Name");
        User newUser = new User(name);
        JSONArray jsonArray = jsonObject.getJSONArray("Assessments");
        for (Object json : jsonArray) {
            JSONObject nextAssessment = (JSONObject) json;
            Assessment newReadAssessment = getAssessmentInfo(nextAssessment);
            newUser.addAssessment(newReadAssessment);
        }
        return newUser;
    }

    //Effects: Parses an Assessment's fields from JSON object and returns the Assessment
    private Assessment getAssessmentInfo(JSONObject jsonObject) {
        int con = jsonObject.getInt("Conscientiousness");
        int emo = jsonObject.getInt("Emotionality");
        int hon = jsonObject.getInt("Honesty-Humility");
        int ext = jsonObject.getInt("Extraversion");
        int ope = jsonObject.getInt("Openness to Experience");
        int agr = jsonObject.getInt("Agreeableness");
        Assessment readAssessment = new Assessment(con,emo,hon,ext,ope,agr);
        return readAssessment;
    }
}

