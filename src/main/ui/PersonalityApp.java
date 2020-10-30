package ui;

import model.App;
import model.Assessment;
import model.Team;
import model.User;
import org.json.JSONObject;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/*
 * Represents the personality test app.
 * Allows users to create teams, users, and assessments.
 */
public class PersonalityApp {
    private static final String JSON_STORE = "./data/App.json";
    private App yourApp;
    private Scanner keyboard;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: Displays introduction and list of current teams then starts the rest of the program
    public PersonalityApp() {
        keyboard = new Scanner(System.in);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        yourApp = new App();
        System.out.println("Welcome to the Digital HEXACO personality test.");
        System.out.println("Would you like to load data from a previous session (y/n)?");
        String loadData = keyboard.nextLine();
        if (loadData.equals("y")) {
            loadApp();
        }
        System.out.println("\nTeam list:");
        System.out.println(yourApp.toString());
        runPersonality();
    }

    //Effects: Displays data for all current teams. Allows user to select a team to get information
    //          associated with the team, or create a new team.
    //          Allows users to view and create user profiles and run assessments.
    public void runPersonality() {
        boolean running = true;
        System.out.println("Please enter your team name (Enter to save your data and exit the program):");
        String teamName = keyboard.nextLine();
        if (teamName.equals("")) {
            running = false;
        }
        while (running) {
            Team currentTeam = yourApp.teamExists(teamName);
            System.out.println("\nYour team:\n" + currentTeam.toString());
            System.out.println("Please enter a team member's name (Enter to return to the team list):");
            String userName = keyboard.nextLine();
            while (!userName.equals("")) {
                userName = displayUserAndAssessment(currentTeam, userName);
            }
            System.out.println("Team List:");
            System.out.println(yourApp.toString());
            System.out.println("Please enter your team name (Enter to save your data and exit the program):");
            teamName = keyboard.nextLine();
            if (teamName.equals("")) {
                running = false;
            }
            exitSequence();
        }
    }

    //Effects: Asks the user if they would like to save App data
    //If requested, saves data to a JSON file and ends the program.
    //Otherwise, ends the program
    public void exitSequence() {
        System.out.println("Would you like to save your data (y/n)?");
        String saveDecision = keyboard.nextLine();
        if (saveDecision.equals("y")) {
            saveApp();
        }
    }

    //Effects: Displays data for the requested user.
    //          If requested, runs a personality assessment and associates it with the requested user.
    //          Prompts for another username.
    public String displayUserAndAssessment(Team currentTeam, String userName) {
        User currentUser = currentTeam.userExists(userName);
        System.out.println("\nTeam member data:");
        System.out.println(currentUser.toString());
        System.out.println("\nWould you like to be assessed (y/n)?");
        String response = keyboard.nextLine();
        while (response.equals("y")) {
            Assessment newAssessment = new Assessment(0, 0, 0, 0, 0, 0);
            runAssessment(currentUser, newAssessment);
            System.out.println("Team member data:");
            System.out.println(currentUser.toString());
            System.out.println("\nWould you like to be assessed (y/n)?");
            response = keyboard.nextLine();
        }
        System.out.println("\nYour team:");
        System.out.println(currentTeam.toString());
        System.out.println("\nPlease enter a team member's name (Enter to return to the team list):");
        userName = keyboard.nextLine();
        return userName;
    }

    //Requires: Responses must be the integers 1,2,3,4 or 5.
    //Effects: Asks the user a series of questions and assigns the responses to the proper personality trait.
    //          Then adds the completed Assessment to the User's assessmentList.
    public void runAssessment(User user, Assessment assessment) {
        System.out.println("\nPlease answer the following questions the the best of your ability:");
        System.out.println("1 = strongly disagree, 2 = disagree, 3 = neutral, 4 = agree, 5 = strongly agree");
        System.out.println(Assessment.QUESTION2C);
        int question2CAnswer = Integer.parseInt(keyboard.nextLine());
        assessment.setConscientiousness(question2CAnswer);
        System.out.println(Assessment.QUESTION3A);
        int question3AAnswer = Integer.parseInt(keyboard.nextLine());
        assessment.setAgreeableness(question3AAnswer);
        System.out.println(Assessment.QUESTION4X);
        int question4XAnswer = Integer.parseInt(keyboard.nextLine());
        assessment.setExtraversion(question4XAnswer);
        System.out.println(Assessment.QUESTION5E);
        int question5EAnswer = Integer.parseInt(keyboard.nextLine());
        assessment.setEmotionality(question5EAnswer);
        System.out.println(Assessment.QUESTION6H);
        int question6HAnswer = Integer.parseInt(keyboard.nextLine());
        assessment.setHonestyHumility(question6HAnswer);
        System.out.println(Assessment.QUESTION7O);
        int question7OAnswer = Integer.parseInt(keyboard.nextLine());
        assessment.setOpennessToExperience(question7OAnswer);
        user.addAssessment(assessment);
        System.out.println("The assessment is complete!\n");
    }

    //Effects: saves the App to a file
    // This class is based on the CPSC 210 Json Serialization Demo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void saveApp() {
        try {
            jsonWriter.open();
            jsonWriter.write(yourApp);
            jsonWriter.close();
            System.out.println("Saved data to: " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //Modifies: this
    //Effects: loads an App from a file
    // This class is based on the CPSC 210 Json Serialization Demo:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void loadApp() {
        try {
            yourApp = jsonReader.read();
            System.out.println("Loaded data from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
