package ui;

import model.Assessment;
import model.Team;
import model.User;

import java.util.*;

/*
 * Represents the personality test app.
 */
public class PersonalityApp {
    private ArrayList<Team> teamList = new ArrayList<>();
    Scanner keyboard = new Scanner(System.in);

    //EFFECTS: Displays introduction and list of current teams then starts the rest of the program
    public PersonalityApp() {
        System.out.println("Welcome to the Digital HEXACO personality test.");
        System.out.println("\nTeam list:");
        System.out.println(this.toString());
        runPersonality();
    }

    //EFFECTS: Prompts for user input. Allows users to create and access teams.
    //          Allows users to create user profiles and complete assessments.
    public void runPersonality() {
        System.out.println("Please enter your team name:");
        String teamName = keyboard.nextLine();
        while (!teamName.equals("")) {
            Team currentTeam = teamExists(teamName);
            System.out.println("\nYour team:");
            System.out.println(currentTeam.toString());
            System.out.println("Please enter a team member's name (Enter to return to the team list):");
            String userName = keyboard.nextLine();
            while (!userName.equals("")) {
                userName = displayUserAndAssessment(currentTeam, userName);
            }
            System.out.println("Team List:");
            System.out.println(this.toString());
            System.out.println("\nPlease enter your team name:");
            teamName = keyboard.nextLine();
        }
    }

    //EFFECTS: Displays user data. Tuns a personality assessment and associates it with the correct user.
    //          Prompts for another username
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
        System.out.println("Your team:");
        System.out.println(currentTeam.toString());
        keyboard.nextLine();
        System.out.println("\nPlease enter a team member's name (Enter to return to the team list):");
        userName = keyboard.nextLine();
        return userName;
    }

    //Effects: If a team name is already used by a Team in the list of Teams, return that Team. Otherwise, return a
    //          new Team with the given team name.
    public Team teamExists(String teamName) {
        boolean teamExists = false;
        Team currentTeam = new Team(teamName);
        for (Team team: teamList) {
            if (teamName.equals(team.getTeamName())) {
                currentTeam = team;
                teamExists = true;
            }
        }
        if (!teamExists) {
            teamList.add(currentTeam);
        }
        return currentTeam;
    }

    //Requires: Responses must be the integers 1,2,3,4 or 5.
    //Effects: Asks the user a series of questions and assigns the responses to the proper personality trait.
    //          Then adds the completed Assessment to the User's assessmentList.
    public void runAssessment(User user, Assessment assessment) {
        System.out.println("\nPlease answer the following questions the the best of your ability:");
        System.out.println("1 = strongly disagree, 2 = disagree, 3 = neutral, 4 = agree, 5 = strongly agree");
        System.out.println(Assessment.QUESTION2C);
        int question2CAnswer = keyboard.nextInt();
        assessment.setConscientiousness(question2CAnswer);
        System.out.println(Assessment.QUESTION3A);
        int question3AAnswer = keyboard.nextInt();
        assessment.setAgreeableness(question3AAnswer);
        System.out.println(Assessment.QUESTION4X);
        int question4XAnswer = keyboard.nextInt();
        assessment.setExtraversion(question4XAnswer);
        System.out.println(Assessment.QUESTION5E);
        int question5EAnswer = keyboard.nextInt();
        assessment.setEmotionality(question5EAnswer);
        System.out.println(Assessment.QUESTION6H);
        int question6HAnswer = keyboard.nextInt();
        assessment.setHonestyHumility(question6HAnswer);
        System.out.println(Assessment.QUESTION7O);
        int question7OAnswer = keyboard.nextInt();
        assessment.setOpennessToExperience(question7OAnswer);
        user.addAssessment(assessment);
        keyboard.nextLine();
        System.out.println("The assessment is complete!\n");
    }

    @Override
    //Effects: Returns a String with a list of teams their contained users
    public String toString() {
        String list = new String();
        if (!teamList.isEmpty()) {
            if (teamList.size() == 1) {
                Team team = teamList.get(0);
                list = team.toString();
            } else {
                for (Team team : teamList) {
                    list = list + team.toString() + "\n";
                }
                list = list.substring(0, list.length() - 1);
            }
        }
        return list;
    }
}
